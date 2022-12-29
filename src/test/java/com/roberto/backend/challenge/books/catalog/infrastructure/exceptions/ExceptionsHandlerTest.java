package com.roberto.backend.challenge.books.catalog.infrastructure.exceptions;

import com.roberto.backend.challenge.books.catalog.domain.exception.BookAlreadyExistsException;
import com.roberto.backend.challenge.books.catalog.domain.exception.BookNotFoundException;
import com.roberto.backend.challenge.books.catalog.domain.exception.PublisherUpdateNotAllowedException;
import java.util.UUID;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionsHandlerTest {

  @Test
  public void shouldReturnHttpStatusConflictWhenThrownBookAlreadyExistsException() {
    final ResponseEntity<Object> response = ExceptionsHandler.handlerBookAlreadyExistsException(new BookAlreadyExistsException(UUID.randomUUID()));
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getBody()).isNotNull();
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
  }

  @Test
  public void shouldReturnHttpStatusNotFoundWhenThrownBookNotFoundException() {
    final ResponseEntity<Object> response = ExceptionsHandler.handlerBookNotFoundException(new BookNotFoundException(UUID.randomUUID()));
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getBody()).isNotNull();
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
  }

  @Test
  public void shouldReturnHttpStatusBadRequestWhenThrownPublisherUpdateNotAllowedException() {
    final ResponseEntity<Object> response = ExceptionsHandler.handlerPublisherUpdateNotAllowedException(new PublisherUpdateNotAllowedException(UUID.randomUUID()));
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getBody()).isNotNull();
    Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
  }

}
