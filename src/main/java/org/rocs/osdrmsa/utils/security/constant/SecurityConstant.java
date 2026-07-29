package org.rocs.osdrmsa.utils.security.constant;

public final class SecurityConstant {

    private SecurityConstant() {
    }

    public static final String AUTH_HEADER = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String[] PUBLIC_URLS = {
            "/login", "/login/**", "/api/health",
            "/swagger-ui.html", "/swagger-ui/**",
            "/v3/api-docs", "/v3/api-docs/**"
    };
}