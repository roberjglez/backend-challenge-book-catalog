package com.roberto.backend.challenge.books.catalog.infrastructure.exceptions;

import com.roberto.backend.challenge.books.catalog.domain.exception.BookAlreadyExistsException;
import com.roberto.backend.challenge.books.catalog.domain.exception.BookNotFoundException;
import com.roberto.backend.challenge.books.catalog.domain.exception.PublisherUpdateNotAllowedException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public final class ExceptionsHandler {

    @ExceptionHandler(value = BookAlreadyExistsException.class)
    public static ResponseEntity<Object> handlerBookAlreadyExistsException(final BookAlreadyExistsException e) {

        final Map<String, Object> errors = new HashMap<>();
        errors.put("message", e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errors);
    }

    @ExceptionHandler(value = BookNotFoundException.class)
    public static ResponseEntity<Object> handlerBookNotFoundException(final BookNotFoundException e) {

        final Map<String, Object> errors = new HashMap<>();
        errors.put("message", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

  @ExceptionHandler(value = PublisherUpdateNotAllowedException.class)
  public static ResponseEntity<Object> handlerPublisherUpdateNotAllowedException(final PublisherUpdateNotAllowedException e) {

    final Map<String, Object> errors = new HashMap<>();
    errors.put("message", e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }
}
