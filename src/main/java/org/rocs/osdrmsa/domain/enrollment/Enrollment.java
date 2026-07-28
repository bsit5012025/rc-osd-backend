package org.rocs.osdrmsa.domain.enrollment;

import jakarta.persistence.*;
import lombok.Data;
import org.rocs.osdrmsa.domain.department.Department;
import org.rocs.osdrmsa.domain.disciplinary.status.DisciplinaryStatus;
import org.rocs.osdrmsa.domain.person.student.Student;

@Entity
@Data
@Table(name = "ENROLLMENT")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENROLLMENTID")
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "STUDENTID")
    private Student student;

    @Column(name = "SCHOOLYEAR")
    private String schoolYear;

    @Column(name = "STUDENTLEVEL")
    private String studentLevel;

    @Column(name = "SECTION")
    private String section;

    @Enumerated(EnumType.STRING)
    @Column(name = "DEPARTMENT")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "DISCIPLINARYSTATUSID")
    private DisciplinaryStatus disciplinaryStatus;
}
