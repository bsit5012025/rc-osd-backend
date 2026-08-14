package org.rocs.osdrmsa.dto.response;

public record LoginResponse(String token, String username, String role) {
}
