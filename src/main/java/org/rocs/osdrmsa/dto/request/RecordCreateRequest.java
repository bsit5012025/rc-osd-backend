package org.rocs.osdrmsa.dto.request;

import java.time.LocalDate;

public record RecordCreateRequest(
        long enrollmentId,
        String employeeId,
        long offenseId,
        LocalDate dateOfViolation,
        long actionId,
        String remarks) {
}