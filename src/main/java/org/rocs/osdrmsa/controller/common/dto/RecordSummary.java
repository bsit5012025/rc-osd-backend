package org.rocs.osdrmsa.controller.common.dto;

import org.rocs.osdrmsa.domain.record.RecordStatus;

public record RecordSummary(long recordId, OffenseSummary offense, RecordStatus status) {
}