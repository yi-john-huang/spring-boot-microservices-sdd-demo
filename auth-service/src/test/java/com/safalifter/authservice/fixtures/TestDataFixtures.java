package com.safalifter.authservice.fixtures;

import com.safalifter.authservice.dto.request.LoginRequest;
import com.safalifter.authservice.dto.request.RegisterRequest;

public class TestDataFixtures {

    public static final String TEST_EMAIL = "test@example.com";
    public static final String TEST_PASSWORD = "password123";
    public static final String TEST_FIRST_NAME = "John";
    public static final String TEST_LAST_NAME = "Doe";

    public static LoginRequest createLoginRequest() {
        return LoginRequest.builder()
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .build();
    }

    public static RegisterRequest createRegisterRequest() {
        return RegisterRequest.builder()
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .firstName(TEST_FIRST_NAME)
                .lastName(TEST_LAST_NAME)
                .build();
    }
}
