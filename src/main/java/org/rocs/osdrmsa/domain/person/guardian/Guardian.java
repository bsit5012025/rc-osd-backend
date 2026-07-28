package org.rocs.osdrmsa.domain.person.guardian;

import jakarta.persistence.*;
import lombok.Data;
import org.rocs.osdrmsa.domain.person.Person;

@Entity
@Data
@Table(name = "GUARDIAN")
public class Guardian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GUARDIANID")
    private Long guardianId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PERSONID")
    private Person person;

    @Column(name = "CONTACTNUMBER")
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "RELATIONSHIP")
    private Relationship relationship;
}
