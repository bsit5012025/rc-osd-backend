package org.rocs.osdrmsa.domain.request;

import jakarta.persistence.*;
import lombok.Data;
import org.rocs.osdrmsa.domain.person.employee.Employee;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "REQUEST")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUESTID")
    private Long requestId;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEEID")
    private Employee employee;

    @Column(name = "DETAILS")
    private String details;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "TYPE")
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private RequestStatus status;

    @Column(name = "DATEPROCESSED")
    private LocalDate dateProcessed;

    @Column(name = "REMARKS")
    private String remarks;
}
