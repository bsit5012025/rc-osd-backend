package org.rocs.osdrmsa.dto.summary;

import org.rocs.osdrmsa.domain.record.RecordStatus;

public record RecordSummary(long recordId, OffenseSummary offense, RecordStatus status) {
}