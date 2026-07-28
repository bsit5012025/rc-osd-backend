package org.rocs.osdrmsa.repository.record;

import org.rocs.osdrmsa.domain.department.Department;
import org.rocs.osdrmsa.domain.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByEnrollmentStudentStudentId(String studentId);

    List<Record> findByEnrollmentDepartmentAndEnrollmentSchoolYear(
            Department department,
            String schoolYear);

    List<Record> findByEnrollmentSchoolYearOrderByDateOfViolationDesc(String schoolYear);

    long countByEnrollmentSchoolYear(String schoolYear);

    long countByDateOfViolation(Date dateOfViolation);

    @Query("SELECT r.offense.offense AS offense, COUNT(r) AS total "
            + "FROM Record r WHERE r.enrollment.schoolYear = :schoolYear "
            + "GROUP BY r.offense.offense ORDER BY COUNT(r) DESC")
    List<OffenseFrequencyProjection> findOffenseFrequencyBySchoolYear(@Param("schoolYear") String schoolYear);

}
