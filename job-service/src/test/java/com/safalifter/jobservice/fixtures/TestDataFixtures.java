package com.safalifter.jobservice.fixtures;

import com.safalifter.jobservice.dto.request.CreateJobRequest;
import com.safalifter.jobservice.dto.request.UpdateJobRequest;
import com.safalifter.jobservice.entity.Job;
import com.safalifter.jobservice.enums.JobStatus;

import java.math.BigDecimal;

public class TestDataFixtures {

    public static final String TEST_TITLE = "Software Engineer";
    public static final String TEST_DESCRIPTION = "Java Developer Position";
    public static final BigDecimal TEST_SALARY = new BigDecimal("75000");
    public static final Long TEST_JOB_ID = 1L;
    public static final Long TEST_USER_ID = 1L;

    public static CreateJobRequest createJobRequest() {
        return CreateJobRequest.builder()
                .title(TEST_TITLE)
                .description(TEST_DESCRIPTION)
                .salary(TEST_SALARY)
                .build();
    }

    public static UpdateJobRequest updateJobRequest() {
        return UpdateJobRequest.builder()
                .title("Senior Software Engineer")
                .description("Senior Java Developer Position")
                .salary(new BigDecimal("95000"))
                .build();
    }

    public static Job createJob() {
        return Job.builder()
                .id(TEST_JOB_ID)
                .title(TEST_TITLE)
                .description(TEST_DESCRIPTION)
                .salary(TEST_SALARY)
                .userId(TEST_USER_ID)
                .status(JobStatus.ACTIVE)
                .build();
    }
}
