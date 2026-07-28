package org.rocs.osdrmsa.domain.login;

import jakarta.persistence.*;
import lombok.Data;
import org.rocs.osdrmsa.domain.person.Person;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "LOGIN")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "PERSONID")
    private Person person;

    @Column(name = "USERNAME", nullable = false, unique = true, length = 25)
    private String username;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "JOIN_DATE")
    private LocalDateTime joinDate;

    @Column(name = "LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "AUTHORITIES")
    private String authorities;

    @Column(name = "IS_ACTIVE")
    private Boolean active;

    @Column(name = "IS_LOCKED")
    private Boolean locked;
}
