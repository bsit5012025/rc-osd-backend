package org.rocs.osdrmsa.repository.enrollment;

import org.rocs.osdrmsa.domain.enrollment.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByStudentStudentId(String studentId);

    Optional<Enrollment> findTopByStudentStudentIdOrderBySchoolYearDesc(String studentId);

}
