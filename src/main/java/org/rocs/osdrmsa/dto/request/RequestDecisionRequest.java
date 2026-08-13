package org.rocs.osdrmsa.dto.request;

import org.rocs.osdrmsa.domain.request.RequestStatus;

public record RequestDecisionRequest(RequestStatus decision, String remarks) {
}
