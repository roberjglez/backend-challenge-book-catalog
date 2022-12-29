package com.roberto.backend.challenge.books.catalog.infrastructure.controller;

import com.roberto.backend.challenge.books.catalog.application.use_case.add.AddBookCommand;
import com.roberto.backend.challenge.books.catalog.application.use_case.add.AddBookUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books-catalog")
@RequiredArgsConstructor
public class AddBookController {

  private final AddBookUseCase addBookUseCase;

  @PostMapping(value = "/add-book", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> addBook(@Valid @RequestBody final AddBookRequest request) {
    addBookUseCase.execute(AddBookCommand.builder()
        .id(request.id)
        .title(request.title)
        .author(request.author)
        .genre(request.genre)
        .publisher(request.publisher)
        .publishedOn(request.publishedOn)
        .build());

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @Builder
  public record AddBookRequest (
      @NotNull UUID id,
      @NotNull String title,
      @NotNull String author,
      @NotNull String genre,
      @NotNull String publisher,
      @NotNull LocalDate publishedOn) {
  }
}
