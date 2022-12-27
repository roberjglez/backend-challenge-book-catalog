package com.roberto.backend.challenge.books.catalog.domain.port.repository;

import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import java.util.UUID;

public interface BookRepository {

  void save(Book book);

  boolean existsById(UUID id);
}
