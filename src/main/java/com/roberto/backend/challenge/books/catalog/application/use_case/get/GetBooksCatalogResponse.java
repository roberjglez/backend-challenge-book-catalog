package com.roberto.backend.challenge.books.catalog.application.use_case.get;

import java.util.List;
import lombok.Builder;

@Builder
public record GetBooksCatalogResponse(List<GetBookResponse> books) {
}
