package com.safalifter.gateway.fixtures;

import org.springframework.http.HttpHeaders;

public class TestDataFixtures {

    public static final String TEST_AUTH_TOKEN = "Bearer test-jwt-token";
    public static final String TEST_USER_EMAIL = "test@example.com";
    public static final String TEST_CORRELATION_ID = "test-correlation-id";

    public static HttpHeaders createAuthHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, TEST_AUTH_TOKEN);
        headers.set("X-Correlation-ID", TEST_CORRELATION_ID);
        return headers;
    }

    public static HttpHeaders createCorsHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ORIGIN, "http://localhost:3000");
        headers.set(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, "POST");
        headers.set(HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS, "Content-Type,Authorization");
        return headers;
    }
}
