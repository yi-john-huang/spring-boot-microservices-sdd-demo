package com.safalifter.notificationservice.fixtures;

import com.safalifter.notificationservice.dto.NotificationRequest;

public class TestDataFixtures {

    public static final String TEST_EMAIL = "test@example.com";
    public static final String TEST_SUBJECT = "Test Notification";
    public static final String TEST_MESSAGE = "This is a test notification message";

    public static NotificationRequest createNotificationRequest() {
        return NotificationRequest.builder()
                .email(TEST_EMAIL)
                .subject(TEST_SUBJECT)
                .message(TEST_MESSAGE)
                .build();
    }
}
