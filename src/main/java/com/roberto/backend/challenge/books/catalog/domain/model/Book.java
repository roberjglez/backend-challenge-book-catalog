package com.roberto.backend.challenge.books.catalog.domain.model;

import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Author;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Id;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Title;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.PublishedOn;

public class Book {

  private Id id;
  private Title title;
  private Author author;
  private PublishedOn publishedOn;
}
