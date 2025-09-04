package com.safalifter.filestorage.fixtures;

import org.springframework.mock.web.MockMultipartFile;

public class TestDataFixtures {

    public static final String TEST_FILE_NAME = "test-file.txt";
    public static final String TEST_CONTENT_TYPE = "text/plain";
    public static final String TEST_FILE_CONTENT = "This is test file content";
    public static final Long TEST_USER_ID = 1L;

    public static MockMultipartFile createTestFile() {
        return new MockMultipartFile(
                "file",
                TEST_FILE_NAME,
                TEST_CONTENT_TYPE,
                TEST_FILE_CONTENT.getBytes()
        );
    }

    public static MockMultipartFile createLargeTestFile() {
        byte[] largeContent = new byte[1024 * 1024]; // 1MB
        return new MockMultipartFile(
                "file",
                "large-file.txt",
                TEST_CONTENT_TYPE,
                largeContent
        );
    }
}
