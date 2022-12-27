package com.roberto.backend.challenge.books.catalog.infrastructure.controller;

import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBookQuery;
import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBookResponse;
import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBookUseCase;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books-catalog")
@RequiredArgsConstructor
public class GetBookController {

  private final GetBookUseCase getBookUseCase;

  @GetMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetBookResponse> getBook(@PathVariable final UUID bookId) {
    final GetBookResponse response = getBookUseCase.get(GetBookQuery.builder()
        .id(bookId)
        .build());

    return ResponseEntity.ok().body(response);
  }
}
