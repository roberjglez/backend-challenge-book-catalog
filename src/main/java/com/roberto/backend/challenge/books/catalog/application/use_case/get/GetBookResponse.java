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
        .id(book.id().getValue())
        .title(book.title().getValue())
        .author(book.author().getValue())
        .genre(book.genre().getValue())
        .publisher(book.publisher().getValue())
        .publishedOn(book.publishedOn().getValue())
        .build();
  }
}
