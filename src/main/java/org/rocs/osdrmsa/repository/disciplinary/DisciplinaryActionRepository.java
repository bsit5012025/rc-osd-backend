package org.rocs.osdrmsa.repository.disciplinary;

import org.rocs.osdrmsa.domain.disciplinary.action.DisciplinaryAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaryActionRepository extends JpaRepository<DisciplinaryAction, Long> {

    List<DisciplinaryAction> findAllByOrderByActionAsc();

    Optional<DisciplinaryAction> findByAction(String action);
}
