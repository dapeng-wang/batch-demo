package org.innolab.batchdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;

@Slf4j
public class LogStepExecutionListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.info("Step Name: " + stepExecution.getStepName());
        log.info("Job Parameters: " + stepExecution.getJobParameters());
        log.info("Execution Context: " + stepExecution.getExecutionContext());
        log.info("Start Time: " + stepExecution.getStartTime());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Step Status: " + stepExecution.getStatus());
        log.info("Exit Status: " + stepExecution.getExitStatus());
        log.info("Read Count: " + stepExecution.getReadCount());
        log.info("Write Count: " + stepExecution.getWriteCount());
        log.info("Commit Count: " + stepExecution.getCommitCount());
        log.info("Skip Count (Read): " + stepExecution.getReadSkipCount());
        log.info("Skip Count (Write): " + stepExecution.getWriteSkipCount());
        log.info("Failure Exceptions: " + stepExecution.getFailureExceptions());
        log.info("End Time: " + stepExecution.getEndTime());

        return stepExecution.getExitStatus();
    }
}
