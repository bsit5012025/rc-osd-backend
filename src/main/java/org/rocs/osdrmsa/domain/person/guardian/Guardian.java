package org.rocs.osdrmsa.domain.person.guardian;

import jakarta.persistence.*;
import lombok.Data;
import org.rocs.osdrmsa.domain.person.Person;

@Entity
@Data
public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guardianID", nullable = false, updatable = false)
    private long guardianID;

    @OneToOne
    @JoinColumn(name = "personID")
    private Person person;

    @Column(name = "contactNumber")
    private String contactNumber;

    @Column(name = "relationship")
    private String relationship;
}
