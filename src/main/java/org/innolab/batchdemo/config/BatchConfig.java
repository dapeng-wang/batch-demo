package org.innolab.batchdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.innolab.batchdemo.model.Book;
import org.innolab.batchdemo.process.BookLineAggregator;
import org.innolab.batchdemo.repository.BookRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final BookRepository bookRepository;
    private final Tasklet zipFileTasklet;
    private final ObjectMapper objectMapper;

    @Bean
    public RepositoryItemReader<Book> reader() {
        RepositoryItemReader<Book> reader = new RepositoryItemReader<>();
        reader.setRepository(bookRepository);
        reader.setMethodName("findAll");

        // Pagination and sorting configuration
        reader.setPageSize(10);
        HashMap<String, Sort.Direction> sortConfiguration = new HashMap<>();
        sortConfiguration.put("isbn", Sort.Direction.ASC);
        reader.setSort(sortConfiguration);
        return reader;
    }

    @Bean
    public FlatFileItemWriter<Book> writer() {
        FlatFileItemWriter<Book> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("output/books.json"));
        writer.setLineAggregator(new BookLineAggregator(objectMapper));
        return writer;
    }

    @Bean
    public Step jsonStep() {
        return new StepBuilder("jsonStep", jobRepository)
                .<Book, Book>chunk(10, transactionManager)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean
    public Step zipStep() {
        return new StepBuilder("zipStep", jobRepository)
                .tasklet(zipFileTasklet, transactionManager)
                .build();
    }

    @Bean
    public Job exportBookJob() {
        return new JobBuilder("exportBookJob", jobRepository)
                .start(jsonStep())
                .next(zipStep())
                .build();
    }
}
