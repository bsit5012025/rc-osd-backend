package org.rocs.osdrmsa.domain.person.student;

import jakarta.persistence.*;
import lombok.Data;
import org.rocs.osdrmsa.domain.department.Department;
import org.rocs.osdrmsa.domain.person.Person;

@Entity
@Data
@Table(name = "STUDENT")
public class Student {

    @Id
    @Column(name = "STUDENTID")
    private String studentId;

    @OneToOne
    @JoinColumn(name = "PERSONID")
    private Person person;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "STUDENTTYPE")
    private String studentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEPARTMENT")
    private Department department;
}
