package com.roberto.backend.challenge.books.catalog.application.use_case.update;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.roberto.backend.challenge.books.catalog.domain.exception.BookNotFoundException;
import com.roberto.backend.challenge.books.catalog.domain.exception.PublisherUpdateNotAllowedException;
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
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateBookPublisherUseCaseTest {

  @InjectMocks
  private UpdateBookPublisherUseCase updateBookPublisherUseCase;

  @Mock
  private BookRepository bookRepository;

  @Test
  public void shouldUpdateBookPublisherCorrectly() {

    final var faker = new Faker();
    final var updateBookPublisherCommand = UpdateBookPublisherCommand.builder()
        .bookId(UUID.randomUUID())
        .publisher(faker.book().publisher())
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
        .findById(updateBookPublisherCommand.bookId());

    updateBookPublisherUseCase.update(updateBookPublisherCommand);

    final var captor = ArgumentCaptor.forClass(Book.class);
    Mockito.verify(bookRepository).save(captor.capture());

    final Book captorBook = captor.getValue();
    Assertions.assertThat(captorBook.getPublisher().getValue()).isEqualTo(updateBookPublisherCommand.publisher());

    Mockito.verify(bookRepository).findById(updateBookPublisherCommand.bookId());
  }

  @Test
  public void shouldThrowsExceptionWhenBookIdDoesNotExistInTheSystem() {

    final var faker = new Faker();
    final var updateBookPublisherCommand = UpdateBookPublisherCommand.builder()
        .bookId(UUID.randomUUID())
        .publisher(faker.book().publisher())
        .build();

    Mockito.doReturn(Optional.empty())
        .when(bookRepository)
        .findById(updateBookPublisherCommand.bookId());

    assertThrows(
        BookNotFoundException.class,
        () -> updateBookPublisherUseCase.update(updateBookPublisherCommand),
        "Book with ID " + updateBookPublisherCommand.bookId() + " does not exist in the system"
    );

    Mockito.verify(bookRepository).findById(updateBookPublisherCommand.bookId());
    Mockito.verify(bookRepository, Mockito.never()).save(any());
  }

  @Test
  public void shouldThrowsExceptionWhenPublisherUpdateIsNotAllowed() {

    final var faker = new Faker();
    final var updateBookPublisherCommand = UpdateBookPublisherCommand.builder()
        .bookId(UUID.randomUUID())
        .publisher(faker.book().publisher())
        .build();

    final var book = Book.builder()
        .id(new Id(UUID.randomUUID()))
        .title(new Title(faker.book().title()))
        .author(new Author(faker.book().author()))
        .genre(new Genre(faker.book().genre()))
        .publisher(new Publisher(faker.book().publisher()))
        .publishedOn(new PublishedOn(LocalDate.now().minusYears(6)))
        .build();

    Mockito.doReturn(Optional.of(book))
        .when(bookRepository)
        .findById(updateBookPublisherCommand.bookId());

    assertThrows(
        PublisherUpdateNotAllowedException.class,
        () -> updateBookPublisherUseCase.update(updateBookPublisherCommand),
        "Publisher update related with book with ID " + updateBookPublisherCommand.bookId() + " not allowed due to the maximum time restriction"
    );

    Mockito.verify(bookRepository).findById(updateBookPublisherCommand.bookId());
    Mockito.verify(bookRepository, Mockito.never()).save(any());
  }
}
