package org.innolab.batchdemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchDemoApplication implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job exportBookJob;

    public static void main(String[] args) {
        SpringApplication.run(BatchDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jobLauncher.run(exportBookJob, new JobParameters());
    }
}