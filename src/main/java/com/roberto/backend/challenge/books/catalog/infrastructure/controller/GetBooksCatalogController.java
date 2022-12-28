package com.roberto.backend.challenge.books.catalog.infrastructure.controller;

import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBooksCatalogResponse;
import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBooksCatalogUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books-catalog")
@RequiredArgsConstructor
public class GetBooksCatalogController {

  private final GetBooksCatalogUseCase getBooksCatalogUseCase;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetBooksCatalogResponse> getBooksCatalog() {
    return ResponseEntity.ok().body(getBooksCatalogUseCase.get());
  }
}
