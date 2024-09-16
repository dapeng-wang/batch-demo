package org.innolab.batchdemo.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.innolab.batchdemo.model.Book;
import org.springframework.batch.item.file.transform.LineAggregator;

@AllArgsConstructor
public class BookLineAggregator implements LineAggregator<Book> {
    private final ObjectMapper objectMapper;

    @Override
    public String aggregate(Book book) {
        try {
            return objectMapper.writeValueAsString(book);
        } catch (Exception e) {
            throw new RuntimeException("Unable to serialize book to JSON", e);
        }
    }
}
