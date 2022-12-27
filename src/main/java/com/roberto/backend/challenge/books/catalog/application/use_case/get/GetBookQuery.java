package com.roberto.backend.challenge.books.catalog.application.use_case.get;

import java.util.UUID;
import lombok.Builder;

@Builder
public record GetBookQuery (UUID id) {

}
