package org.rocs.osdrmsa.dto.response;

import org.rocs.osdrmsa.dto.summary.ActionSummary;
import org.rocs.osdrmsa.dto.summary.EmployeeSummary;
import org.rocs.osdrmsa.dto.summary.EnrollmentSummary;
import org.rocs.osdrmsa.dto.summary.OffenseSummary;
import org.rocs.osdrmsa.domain.record.RecordStatus;

import java.time.LocalDate;

public record AppealResponse(
        Long appealId,
        String studentId,
        String studentName,
        String offense,
        String message,
        String status,
        String remarks,
        LocalDate dateSubmitted,
        LocalDate dateProcessed
) {
    public static record RecordResponse(
            long recordId,
            EnrollmentSummary enrollment,
            EmployeeSummary employee,
            OffenseSummary offense,
            LocalDate dateOfViolation,
            ActionSummary action,
            LocalDate dateOfResolution,
            String remarks,
            RecordStatus status) {
    }
}
