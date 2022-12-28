package com.roberto.backend.challenge.books.catalog.application.use_case.get;

import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;

@Builder
public record GetBookResponse (
    UUID id,
    String title,
    String author,
    String genre,
    String publisher,
    LocalDate publishedOn
) {

  public static GetBookResponse from(final Book book) {
    return GetBookResponse.builder()
        .id(book.getId().value())
        .title(book.getTitle().value())
        .author(book.getAuthor().value())
        .genre(book.getGenre().value())
        .publisher(book.getPublisher().value())
        .publishedOn(book.getPublishedOn().value())
        .build();
  }
}
