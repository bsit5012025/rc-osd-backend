package org.rocs.osdrmsa.service.login.impl;

import lombok.RequiredArgsConstructor;
import org.rocs.osdrmsa.domain.login.Login;
import org.rocs.osdrmsa.dto.response.LockedAccountResponse;
import org.rocs.osdrmsa.exception.AccountLockedException;
import org.rocs.osdrmsa.exception.InvalidCredentialsException;
import org.rocs.osdrmsa.repository.login.LoginRepository;
import org.rocs.osdrmsa.service.login.LoginService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private static final String REACTIVATION_MESSAGE =
            "Proceed to prefect office to request for account reactivation";

    private static final int MAX_LOGIN_ATTEMPTS = 5;

    private static final DateTimeFormatter DOB_PASSWORD_FORMAT =
            DateTimeFormatter.ofPattern("MMddyy");

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Login authenticate(
            String username,
            String password
    ) {

        if (username == null || username.isBlank()
                || password == null || password.isBlank()) {

            throw new InvalidCredentialsException(
                    "Username and password are required."
            );
        }

        Login login = loginRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid username or password."
                        )
                );

        boolean isAdmin =
                login.getRole() != null
                        && "ROLE_ADMIN".equals(
                        login.getRole().name()
                );

        if (!isAdmin && login.isLocked()) {
            throw new AccountLockedException(
                    REACTIVATION_MESSAGE
            );
        }

        if (!passwordEncoder.matches(
                password,
                login.getPassword()
        )) {

            if (isAdmin) {

                login.setFailedLoginAttempts(0);
                loginRepository.save(login);

                throw new InvalidCredentialsException(
                        "Invalid username or password."
                );
            }

            int failedAttempts =
                    login.getFailedLoginAttempts() + 1;

            login.setFailedLoginAttempts(
                    failedAttempts
            );

            if (failedAttempts >= MAX_LOGIN_ATTEMPTS) {

                String defaultPassword =
                        generateDefaultPassword(login);

                login.setPassword(
                        passwordEncoder.encode(
                                defaultPassword
                        )
                );

                login.setLocked(true);
                login.setActive(false);

                loginRepository.save(login);

                throw new AccountLockedException(
                        REACTIVATION_MESSAGE
                );
            }

            loginRepository.save(login);

            throw new InvalidCredentialsException(
                    "Invalid username or password. "
                            + "Attempt "
                            + failedAttempts
                            + " of "
                            + MAX_LOGIN_ATTEMPTS
                            + "."
            );
        }

        if (login.isLocked()) {
            throw new AccountLockedException(
                    REACTIVATION_MESSAGE
            );
        }

        login.setFailedLoginAttempts(0);
        login.setLastLoginDate(new Date());

        return loginRepository.save(login);
    }

    @Override
    public Optional<Login> getByUsername(
            String username
    ) {

        if (username == null || username.isBlank()) {
            return Optional.empty();
        }

        return loginRepository.findByUsername(username);
    }

    @Override
    public List<LockedAccountResponse> getLockedAccounts() {

        return loginRepository
                .findByLockedTrue()
                .stream()
                .map(login ->
                        new LockedAccountResponse(
                                login.getUsername(),
                                login.getRole() != null
                                        ? login.getRole().name()
                                        : null,
                                login.getFailedLoginAttempts(),
                                login.isLocked(),
                                login.isActive()
                        )
                )
                .toList();
    }

    @Override
    public void unlockAccount(
            String username
    ) {

        Login login = loginRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "User account not found."
                        )
                );

        login.setLocked(false);
        login.setActive(true);
        login.setFailedLoginAttempts(0);

        loginRepository.save(login);
    }

    @Override
    public void toggleLockAccount(
            String username
    ) {

        Login login = loginRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "User account not found."
                        )
                );

        boolean newLockedState =
                !login.isLocked();

        login.setLocked(newLockedState);
        login.setActive(!newLockedState);

        if (!newLockedState) {
            login.setFailedLoginAttempts(0);
        }

        loginRepository.save(login);
    }

    private String generateDefaultPassword(
            Login login
    ) {

        if (login.getPerson() == null) {
            throw new IllegalArgumentException(
                    "No person record found for this account."
            );
        }

        String lastName =
                login.getPerson()
                        .getLastName();

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException(
                    "Last name is required to generate the default password."
            );
        }

        if (login.getPerson()
                .getDateOfBirth() == null) {

            throw new IllegalArgumentException(
                    "Date of birth is required to generate the default password."
            );
        }

        String formattedDate =
                login.getPerson()
                        .getDateOfBirth()
                        .format(DOB_PASSWORD_FORMAT);

        String formattedLastName =
                lastName.trim()
                        .toLowerCase()
                        .replaceAll("\\s+", "");

        return formattedLastName + formattedDate;
    }

    @Override
    public void changePassword(
            String username,
            String currentPassword,
            String newPassword
    ) {

        if (username == null || username.isBlank()
                || currentPassword == null
                || currentPassword.isBlank()
                || newPassword == null
                || newPassword.isBlank()) {

            throw new IllegalArgumentException(
                    "All password fields are required."
            );
        }

        Login login = loginRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "User account not found."
                        )
                );

        if (!passwordEncoder.matches(
                currentPassword,
                login.getPassword()
        )) {

            throw new InvalidCredentialsException(
                    "Current password is incorrect."
            );
        }

        if (newPassword.length() < 8) {

            throw new IllegalArgumentException(
                    "Password must be at least 8 characters long."
            );
        }

        if (!newPassword.matches(".*[A-Z].*")) {

            throw new IllegalArgumentException(
                    "Password must contain at least one uppercase letter."
            );
        }

        if (!newPassword.matches(".*\\d.*")) {

            throw new IllegalArgumentException(
                    "Password must contain at least one number."
            );
        }

        if (passwordEncoder.matches(
                newPassword,
                login.getPassword()
        )) {

            throw new IllegalArgumentException(
                    "New password must be different from the current password."
            );
        }

        login.setPassword(
                passwordEncoder.encode(
                        newPassword
                )
        );

        loginRepository.save(login);
    }
}