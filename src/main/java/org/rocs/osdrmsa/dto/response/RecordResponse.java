package org.rocs.osdrmsa.dto.response;

import org.rocs.osdrmsa.controller.common.dtosummary.ActionSummary;
import org.rocs.osdrmsa.controller.common.dtosummary.EmployeeSummary;
import org.rocs.osdrmsa.controller.common.dtosummary.EnrollmentSummary;
import org.rocs.osdrmsa.controller.common.dtosummary.OffenseSummary;
import org.rocs.osdrmsa.domain.record.RecordStatus;

import java.time.LocalDate;

public record RecordResponse(
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