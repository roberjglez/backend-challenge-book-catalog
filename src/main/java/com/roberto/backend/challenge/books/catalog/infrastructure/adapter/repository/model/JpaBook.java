package com.roberto.backend.challenge.books.catalog.infrastructure.adapter.repository.model;

import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Author;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Genre;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.PublishedOn;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Publisher;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Title;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
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
        .id(book.getId().getValue())
        .title(book.getTitle().getValue())
        .author(book.getAuthor().getValue())
        .genre(book.getGenre().getValue())
        .publisher(book.getPublisher().getValue())
        .publishedOn(book.getPublishedOn().getValue())
        .build();
  }

  public Book toDomain() {
    return Book.builder()
        .id(new com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Id(id))
        .title(new Title(title))
        .author(new Author(author))
        .genre(new Genre(genre))
        .publisher(new Publisher(publisher))
        .publishedOn(new PublishedOn(publishedOn))
        .build();
  }
}
