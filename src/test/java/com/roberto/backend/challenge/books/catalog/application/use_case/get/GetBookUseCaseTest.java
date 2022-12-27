package com.roberto.backend.challenge.books.catalog.application.use_case.get;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.roberto.backend.challenge.books.catalog.domain.exception.BookNotFoundException;
import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Author;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Genre;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Id;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.PublishedOn;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Publisher;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Title;
import com.roberto.backend.challenge.books.catalog.domain.port.repository.BookRepository;
import java.time.LocalDate;
import java.util.Optional;
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
public class GetBookUseCaseTest {

  @InjectMocks
  private GetBookUseCase getBookUseCase;

  @Mock
  private BookRepository bookRepository;

  @Test
  public void shouldGetBookCorrectly() {

    final var faker = new Faker();
    final var getBookQuery = GetBookQuery.builder()
        .id(UUID.randomUUID())
        .build();
    final var book = Book.builder()
        .id(new Id(UUID.randomUUID()))
        .title(new Title(faker.book().title()))
        .author(new Author(faker.book().author()))
        .genre(new Genre(faker.book().genre()))
        .publisher(new Publisher(faker.book().publisher()))
        .publishedOn(new PublishedOn(LocalDate.now().minusMonths(1)))
        .build();

    Mockito.doReturn(Optional.of(book))
        .when(bookRepository)
        .findById(getBookQuery.id());

    final GetBookResponse response = getBookUseCase.get(getBookQuery);

    Assertions.assertThat(response)
        .extracting("id", "title", "author", "genre", "publisher", "publishedOn")
        .containsExactly(
            book.id().getValue(),
            book.title().getValue(),
            book.author().getValue(),
            book.genre().getValue(),
            book.publisher().getValue(),
            book.publishedOn().getValue()
        );

    Mockito.verify(bookRepository).findById(getBookQuery.id());
  }

  @Test
  public void shouldThrowsExceptionWhenBookIdDoesNotExistInTheSystem() {

    final var getBookQuery = GetBookQuery.builder()
        .id(UUID.randomUUID())
        .build();

    Mockito.doReturn(Optional.empty())
        .when(bookRepository)
        .findById(getBookQuery.id());

    assertThrows(
        BookNotFoundException.class,
        () -> getBookUseCase.get(getBookQuery),
        "Book with ID " + getBookQuery.id() + " does not exist in the system"
    );

    Mockito.verify(bookRepository).findById(getBookQuery.id());
  }
}
