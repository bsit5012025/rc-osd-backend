package org.rocs.osdrmsa.domain.person.student.guardian;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentGuardianId implements Serializable {

    @Column(name = "STUDENTID")
    private String studentId;

    @Column(name = "GUARDIANID")
    private Long guardianId;
}
