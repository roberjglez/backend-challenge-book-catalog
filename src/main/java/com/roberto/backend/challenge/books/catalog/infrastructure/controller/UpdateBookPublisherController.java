package com.roberto.backend.challenge.books.catalog.infrastructure.controller;

import com.roberto.backend.challenge.books.catalog.application.use_case.update.UpdateBookPublisherCommand;
import com.roberto.backend.challenge.books.catalog.application.use_case.update.UpdateBookPublisherUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books-catalog")
@RequiredArgsConstructor
public class UpdateBookPublisherController {

  private final UpdateBookPublisherUseCase updateBookPublisherUseCase;

  @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> updateBookPublisher(@Valid @RequestBody final UpdateBookPublisherRequest request) {
    updateBookPublisherUseCase.update(UpdateBookPublisherCommand.builder()
        .bookId(request.bookId)
        .publisher(request.publisher)
        .build());

    return ResponseEntity.ok().build();
  }

  @Builder
  public record UpdateBookPublisherRequest(@NotNull UUID bookId, @NotNull String publisher) {
  }

}
