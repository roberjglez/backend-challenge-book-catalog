package com.roberto.backend.challenge.books.catalog.infrastructure.adapter.repository;

import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.port.repository.BookRepository;
import com.roberto.backend.challenge.books.catalog.infrastructure.adapter.repository.model.JpaBook;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaBookRepositoryAdapter implements BookRepository {

  private final JpaBookRepository jpaBookRepository;

  @Override
  public void save(final Book book) {
    jpaBookRepository.save(JpaBook.fromDomain(book));
  }

  @Override
  public Optional<Book> findById(final UUID id) {
    return jpaBookRepository.findById(id)
        .map(JpaBook::toDomain);
  }

  @Override
  public boolean existsById(final UUID id) {
    return jpaBookRepository.existsById(id);
  }
}
