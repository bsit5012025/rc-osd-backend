package org.rocs.osdrmsa.domain.disciplinary.status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "DISCIPLINARYSTATUS")
public class DisciplinaryStatus {

    @Id
    @Column(name = "DISCIPLINARYSTATUSID")
    private Long disciplinaryStatusId;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "DESCRIPTION")
    private String description;
}
