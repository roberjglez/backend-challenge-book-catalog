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
        .id(book.getId().getValue())
        .title(book.getTitle().getValue())
        .author(book.getAuthor().getValue())
        .genre(book.getGenre().getValue())
        .publisher(book.getPublisher().getValue())
        .publishedOn(book.getPublishedOn().getValue())
        .build();
  }
}
