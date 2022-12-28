package com.roberto.backend.challenge.books.catalog.domain.port.repository;

import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

  void save(Book book);

  Optional<Book> findById(UUID id);

  List<Book> findAll();

  boolean existsById(UUID id);
}
