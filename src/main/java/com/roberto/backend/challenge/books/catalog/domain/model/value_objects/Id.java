package com.roberto.backend.challenge.books.catalog.domain.model.value_objects;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Id {
  private UUID value;
}
