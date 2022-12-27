package com.roberto.backend.challenge.books.catalog.application.use_case.get;

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

}
