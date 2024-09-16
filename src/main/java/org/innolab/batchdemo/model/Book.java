package org.innolab.batchdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Book {
    @Id
    private Long id;

    private String isbn;
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;
}
