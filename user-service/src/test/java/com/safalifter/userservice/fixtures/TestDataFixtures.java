package com.safalifter.userservice.fixtures;

import com.safalifter.userservice.dto.request.CreateUserRequest;
import com.safalifter.userservice.dto.request.UpdateUserRequest;
import com.safalifter.userservice.entity.User;

public class TestDataFixtures {

    public static final String TEST_EMAIL = "test@example.com";
    public static final String TEST_FIRST_NAME = "John";
    public static final String TEST_LAST_NAME = "Doe";
    public static final Long TEST_USER_ID = 1L;

    public static CreateUserRequest createUserRequest() {
        return CreateUserRequest.builder()
                .email(TEST_EMAIL)
                .firstName(TEST_FIRST_NAME)
                .lastName(TEST_LAST_NAME)
                .build();
    }

    public static UpdateUserRequest updateUserRequest() {
        return UpdateUserRequest.builder()
                .firstName("Jane")
                .lastName("Smith")
                .build();
    }

    public static User createUser() {
        return User.builder()
                .id(TEST_USER_ID)
                .email(TEST_EMAIL)
                .firstName(TEST_FIRST_NAME)
                .lastName(TEST_LAST_NAME)
                .build();
    }
}
