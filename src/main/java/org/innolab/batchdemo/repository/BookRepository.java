package org.innolab.batchdemo.repository;

import org.innolab.batchdemo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
