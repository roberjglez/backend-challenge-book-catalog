package com.roberto.backend.challenge.books.catalog.application.use_case.get;

import com.roberto.backend.challenge.books.catalog.domain.exception.BookNotFoundException;
import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.port.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBookUseCase {

  private final BookRepository bookRepository;

  public GetBookResponse get(final GetBookQuery getBookQuery) {
    final Book book = bookRepository.findById(getBookQuery.id())
        .orElseThrow(() -> new BookNotFoundException(getBookQuery.id()));

    return GetBookResponse.builder()
        .id(book.id().getValue())
        .title(book.title().getValue())
        .author(book.author().getValue())
        .genre(book.genre().getValue())
        .publisher(book.publisher().getValue())
        .publishedOn(book.publishedOn().getValue())
        .build();
  }
}
