package org.rocs.osdrmsa.dto.request;

import java.time.LocalDate;

public record RecordUpdateRequest(
        long recordId,
        long enrollmentId,
        String employeeId,
        long offenseId,
        LocalDate dateOfViolation,
        long actionId,
        String remarks) {
}