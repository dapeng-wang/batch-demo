package org.innolab.batchdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;

@Slf4j
public class LogJobExecutionListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Before Job: " + jobExecution.getJobInstance().getJobName());
        log.info("Job Parameters: " + jobExecution.getJobParameters());
        log.info("Job Start Time: " + jobExecution.getStartTime());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("After Job: " + jobExecution.getJobInstance().getJobName());
        log.info("Job Status: " + jobExecution.getStatus());
        log.info("Job End Time: " + jobExecution.getEndTime());

        if (jobExecution.getStatus().isUnsuccessful()) {
            log.info("Job failed with exceptions: " + jobExecution.getAllFailureExceptions());
        } else {
            log.info("Job completed successfully.");
        }
    }
}
