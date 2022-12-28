package com.roberto.backend.challenge.books.catalog.application.use_case.get;

import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Author;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Genre;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Id;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.PublishedOn;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Publisher;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Title;
import com.roberto.backend.challenge.books.catalog.domain.port.repository.BookRepository;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import net.datafaker.Faker;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GetBooksCatalogUseCaseTest {

  @InjectMocks
  private GetBooksCatalogUseCase getBooksCatalogUseCase;

  @Mock
  private BookRepository bookRepository;

  @Test
  public void shouldGetBooksCatalogCorrectly() {

    final var faker = new Faker();
    final var book1 = Book.builder()
        .id(new Id(UUID.randomUUID()))
        .title(new Title(faker.book().title()))
        .author(new Author(faker.book().author()))
        .genre(new Genre(faker.book().genre()))
        .publisher(new Publisher(faker.book().publisher()))
        .publishedOn(new PublishedOn(LocalDate.now().minusMonths(1)))
        .build();

    final var book2 = Book.builder()
        .id(new Id(UUID.randomUUID()))
        .title(new Title(faker.book().title()))
        .author(new Author(faker.book().author()))
        .genre(new Genre(faker.book().genre()))
        .publisher(new Publisher(faker.book().publisher()))
        .publishedOn(new PublishedOn(LocalDate.now().minusMonths(2)))
        .build();

    Mockito.doReturn(List.of(book1, book2))
        .when(bookRepository)
        .findAll();

    final GetBooksCatalogResponse response = getBooksCatalogUseCase.get();

    Assertions.assertThat(response.books())
        .isNotNull().hasSize(2)
        .extracting("id", "title", "author", "genre", "publisher", "publishedOn")
        .containsExactly(
            Assertions.tuple(
                book1.getId().getValue(),
                book1.getTitle().getValue(),
                book1.getAuthor().getValue(),
                book1.getGenre().getValue(),
                book1.getPublisher().getValue(),
                book1.getPublishedOn().getValue()
            ),
            Assertions.tuple(
                book2.getId().getValue(),
                book2.getTitle().getValue(),
                book2.getAuthor().getValue(),
                book2.getGenre().getValue(),
                book2.getPublisher().getValue(),
                book2.getPublishedOn().getValue()
            )
        );

    Mockito.verify(bookRepository).findAll();
  }

  @Test
  public void shouldGetBookCatalogCorrectlyWhenThereAreNotBooks() {

    Mockito.doReturn(Collections.emptyList())
        .when(bookRepository)
        .findAll();

    final GetBooksCatalogResponse response = getBooksCatalogUseCase.get();

    Assertions.assertThat(response.books()).isEmpty();
  }
}
