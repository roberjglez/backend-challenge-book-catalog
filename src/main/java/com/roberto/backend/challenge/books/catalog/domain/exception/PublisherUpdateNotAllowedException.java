package com.roberto.backend.challenge.books.catalog.domain.exception;

import java.util.UUID;

public class PublisherUpdateNotAllowedException
    extends RuntimeException{

  public PublisherUpdateNotAllowedException(final UUID id) {
    super("Publisher update related with book with ID " + id + " not allowed due to the maximum time restriction");
  }
}
