package com.roberto.backend.challenge.books.catalog.application.use_case.add;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;

@Builder
public record AddBookCommand (
    UUID id,
    String title,
    String author,
    String genre,
    String publisher,
    LocalDate publishedOn) {
}
