package com.roberto.backend.challenge.books.catalog.application.use_case.update;

import java.util.UUID;
import lombok.Builder;

@Builder
public record UpdateBookPublisherCommand (UUID bookId, String publisher) {

}
