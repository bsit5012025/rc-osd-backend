package org.rocs.osdrmsa.repository.offense;

import org.rocs.osdrmsa.domain.offense.Offense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffenseRepository extends JpaRepository<Offense, Long> {

    Optional<Offense> findByOffense(String offense);

    List<Offense> findAllByOrderByOffenseAsc();
}

