package com.roberto.backend.challenge.books.catalog.infrastructure.adapter.repository.model;

import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Author;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Genre;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Id;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.PublishedOn;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Publisher;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Title;
import java.time.LocalDate;
import java.util.UUID;
import net.datafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class JpaBookTest {

  @Test
  public void shouldMapBookFromDomain() {

    final var faker = new Faker();
    final var bookDomain = Book.builder()
        .id(new Id(UUID.randomUUID()))
        .title(new Title(faker.book().title()))
        .author(new Author(faker.book().author()))
        .genre(new Genre(faker.book().genre()))
        .publisher(new Publisher(faker.book().publisher()))
        .publishedOn(new PublishedOn(LocalDate.now().minusMonths(1)))
        .build();

    final JpaBook jpaBook = JpaBook.fromDomain(bookDomain);

    Assertions.assertThat(jpaBook)
        .extracting("id", "title", "author", "genre", "publisher", "publishedOn")
        .containsExactly(
            bookDomain.getId().getValue(),
            bookDomain.getTitle().getValue(),
            bookDomain.getAuthor().getValue(),
            bookDomain.getGenre().getValue(),
            bookDomain.getPublisher().getValue(),
            bookDomain.getPublishedOn().getValue()
        );
  }

  @Test
  public void shouldMapJpaBookToDomain() {

    final var faker = new Faker();
    final var jpaBook = JpaBook.builder()
        .id(UUID.randomUUID())
        .title(faker.book().title())
        .author(faker.book().author())
        .genre(faker.book().genre())
        .publisher(faker.book().publisher())
        .publishedOn(LocalDate.now().minusMonths(1))
        .build();

    final Book bookDomain = jpaBook.toDomain();

    Assertions.assertThat(bookDomain)
        .extracting("id.value", "title.value", "author.value", "genre.value", "publisher.value", "publishedOn.value")
        .containsExactly(
            jpaBook.getId(),
            jpaBook.getTitle(),
            jpaBook.getAuthor(),
            jpaBook.getGenre(),
            jpaBook.getPublisher(),
            jpaBook.getPublishedOn()
        );
  }
}
