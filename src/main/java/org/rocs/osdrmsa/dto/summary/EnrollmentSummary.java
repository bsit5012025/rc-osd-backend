package org.rocs.osdrmsa.dto.summary;

import org.rocs.osdrmsa.domain.department.Department;

public record EnrollmentSummary(
        long enrollmentId,
        StudentSummary student,
        String schoolYear,
        String studentLevel,
        String section,
        Department department) {
}