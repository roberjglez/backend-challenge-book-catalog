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
            bookDomain.id().getValue(),
            bookDomain.title().getValue(),
            bookDomain.author().getValue(),
            bookDomain.genre().getValue(),
            bookDomain.publisher().getValue(),
            bookDomain.publishedOn().getValue()
        );
  }
}
