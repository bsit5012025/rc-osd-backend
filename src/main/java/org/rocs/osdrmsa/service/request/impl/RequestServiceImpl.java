package org.rocs.osdrmsa.service.request.impl;

import lombok.RequiredArgsConstructor;
import org.rocs.osdrmsa.domain.department.Department;
import org.rocs.osdrmsa.domain.login.Login;
import org.rocs.osdrmsa.domain.person.Person;
import org.rocs.osdrmsa.domain.person.employee.Employee;
import org.rocs.osdrmsa.domain.request.Request;
import org.rocs.osdrmsa.domain.request.RequestStatus;
import org.rocs.osdrmsa.repository.employee.EmployeeRepository;
import org.rocs.osdrmsa.repository.login.LoginRepository;
import org.rocs.osdrmsa.repository.request.RequestRepository;
import org.rocs.osdrmsa.service.request.RequestService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final LoginRepository loginRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Request submitRequest(Request request) {

        if (request.getEmployeeID() == null || request.getEmployeeID().isBlank()) {
            throw new IllegalArgumentException("Requesting employeeID is required.");
        }
        if (request.getType() == null || request.getType().isBlank()) {
            throw new IllegalArgumentException("Request type is required.");
        }

        request.setRequestID(0);
        request.setStatus(RequestStatus.PENDING);
        request.setDateProcessed(null);
        request.setRemarks(null);

        return requestRepository.save(request);
    }

    @Override
    public Request processRequest(Long requestId, RequestStatus decision, String remarks) {

        if (decision != RequestStatus.APPROVED && decision != RequestStatus.DENIED) {
            throw new IllegalArgumentException("A request can only be processed to APPROVED or DENIED.");
        }

        Request request = requestRepository.findById(requestId).orElseThrow(() ->
                        new NoSuchElementException("Request not found: " + requestId));

        if (request.getStatus() != RequestStatus.PENDING) {
            throw new IllegalArgumentException("Request " + requestId + " has already been processed (" +
                            request.getStatus() + ").");
        }

        request.setStatus(decision);
        request.setRemarks(remarks);
        request.setDateProcessed(new Date());

        return requestRepository.save(request);
    }

    @Override
    public List<Request> getByEmployeeId(String employeeId) {
        return requestRepository.findByEmployeeID(employeeId);
    }

    @Override
    public List<Request> getByStatus(RequestStatus status) {
        return requestRepository.findByStatus(status);
    }

    @Override
    public List<Request> getAll() {
        return requestRepository.findAll();
    }

    private Employee getLoggedInEmployee(String username) {

        Login login = loginRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException(
                "Logged-in user not found."));

        Person person = login.getPerson();

        if (person == null) {
            throw new IllegalStateException("No person is associated with this account.");
        }

        return employeeRepository.findByPersonPersonId(person.getPersonId()).orElseThrow(() ->
                        new NoSuchElementException("Employee record not found."));
    }

    @Override
    public List<Request> getMyDepartmentRequests(String username) {

        Employee employee = getLoggedInEmployee(username);

        Department department = employee.getDepartment();

        if (department == null) {throw new IllegalStateException("No department is assigned to this employee.");}

        List<Employee> employees = employeeRepository.findByDepartment(department);
        List<String> employeeIds = employees.stream().map(Employee::getEmployeeId).toList();

        if (employeeIds.isEmpty()) {
            return List.of();
        }

        return requestRepository.findByEmployeeIDIn(employeeIds);
    }

    @Override
    public String getMyDepartmentName(String username) {

        Employee employee = getLoggedInEmployee(username);

        if (employee.getDepartment() == null) {
            throw new IllegalStateException("No department is assigned to this employee.");}

        return employee.getDepartment().name();
    }
}
