package org.rocs.osdrmsa.domain.person.student.guardian;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.rocs.osdrmsa.domain.person.guardian.Guardian;
import org.rocs.osdrmsa.domain.person.student.Student;

@Entity
@Table(name = "STUDENTGUARDIAN")
@Getter
@Setter
public class StudentGuardian {

    @EmbeddedId
    private StudentGuardianId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "STUDENTID",
            referencedColumnName = "STUDENTID",
            insertable = false,
            updatable = false
    )
    private Student student;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "GUARDIANID",
            referencedColumnName = "GUARDIANID",
            insertable = false,
            updatable = false
    )
    private Guardian guardian;
}
