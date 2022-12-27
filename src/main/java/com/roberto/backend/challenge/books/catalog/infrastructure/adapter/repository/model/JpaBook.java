package com.roberto.backend.challenge.books.catalog.infrastructure.adapter.repository.model;

import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;

@Builder
@Entity
@Table(name = "book")
public class JpaBook {

  @Id
  @Column(name = "id")
  private UUID id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "author", nullable = false)
  private String author;

  @Column(name = "genre", nullable = false)
  private String genre;

  @Column(name = "publisher", nullable = false)
  private String publisher;

  @Column(name = "published_on", nullable = false)
  private LocalDate publishedOn;


  public static JpaBook fromDomain(final Book book) {
    return JpaBook.builder()
        .id(book.id().getValue())
        .title(book.title().getValue())
        .author(book.author().getValue())
        .genre(book.genre().getValue())
        .publisher(book.publisher().getValue())
        .publishedOn(book.publishedOn().getValue())
        .build();
  }
}
