package com.roberto.backend.challenge.books.catalog.domain.exception;

import java.util.UUID;

public class BookNotFoundException
    extends RuntimeException{

  public BookNotFoundException(final UUID id) {
    super("Book with ID " + id + " does not exist in the system");
  }
}
