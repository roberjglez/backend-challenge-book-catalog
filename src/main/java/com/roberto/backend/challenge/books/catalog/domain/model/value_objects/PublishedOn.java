package com.roberto.backend.challenge.books.catalog.domain.model.value_objects;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PublishedOn {
  private LocalDate value;
}
