package com.roberto.backend.challenge.books.catalog.domain.exception;

import java.util.UUID;

public class BookAlreadyExistsException
    extends RuntimeException{

  public BookAlreadyExistsException(final UUID id) {
    super("Book with ID " + id + " already exists in the system");
  }
}
