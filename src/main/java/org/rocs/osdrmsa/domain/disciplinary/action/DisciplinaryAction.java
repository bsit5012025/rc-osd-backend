package org.rocs.osdrmsa.domain.disciplinary.action;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "DISCIPLINARYACTION")
public class DisciplinaryAction {

    @Id
    @Column(name = "ACTIONID")
    private Long actionId;

    @Column(name = "ACTION")
    private String action;

    @Column(name = "DESCRIPTION")
    private String description;
}
