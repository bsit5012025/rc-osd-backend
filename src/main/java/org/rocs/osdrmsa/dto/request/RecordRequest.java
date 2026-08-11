package org.rocs.osdrmsa.dto.request;

import java.time.LocalDate;

public record RecordRequest(
        Long enrollmentId,
        String employeeId,
        Long offenseId,
        LocalDate dateOfViolation
) {
}