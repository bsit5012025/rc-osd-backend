package org.rocs.osdrmsa.dto.response;

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
}
