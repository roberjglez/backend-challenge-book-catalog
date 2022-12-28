package com.roberto.backend.challenge.books.catalog.domain.model;

import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Author;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Genre;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Id;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Publisher;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Title;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.PublishedOn;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Book {

  private Id id;
  private Title title;
  private Author author;
  private Genre genre;
  private Publisher publisher;
  private PublishedOn publishedOn;

  public void updatePublisher(final Publisher newPublisher) {
    this.publisher = newPublisher;
  }
}
