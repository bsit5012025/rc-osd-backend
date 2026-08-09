package org.rocs.osdrmsa.controller.common.dtosummary;

import org.rocs.osdrmsa.domain.record.RecordStatus;

public record RecordSummary(long recordId, OffenseSummary offense, RecordStatus status) {
}