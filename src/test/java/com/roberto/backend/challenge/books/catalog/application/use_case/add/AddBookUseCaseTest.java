package com.roberto.backend.challenge.books.catalog.application.use_case.add;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.roberto.backend.challenge.books.catalog.domain.exception.BookAlreadyExistsException;
import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.port.repository.BookRepository;
import java.time.LocalDate;
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
public class AddBookUseCaseTest {

  @InjectMocks
  private AddBookUseCase addBookUseCase;

  @Mock
  private BookRepository bookRepository;

  @Test
  public void shouldAddBookCorrectly() {

    final var faker = new Faker();
    final var addBookCommand = AddBookCommand.builder()
        .id(UUID.randomUUID())
        .title(faker.book().title())
        .author(faker.book().author())
        .genre(faker.book().genre())
        .publisher(faker.book().publisher())
        .publishedOn(LocalDate.now().minusMonths(1))
        .build();

    Mockito.doReturn(false)
        .when(bookRepository)
        .existsById(addBookCommand.id());

    addBookUseCase.execute(addBookCommand);

    final var captor = ArgumentCaptor.forClass(Book.class);
    Mockito.verify(bookRepository).save(captor.capture());

    final Book captorBook = captor.getValue();
    Assertions.assertThat(captorBook)
        .extracting("id.value", "title.value", "author.value", "genre.value", "publisher.value", "publishedOn.value")
        .containsExactly(
            addBookCommand.id(),
            addBookCommand.title(),
            addBookCommand.author(),
            addBookCommand.genre(),
            addBookCommand.publisher(),
            addBookCommand.publishedOn()
        );

    Mockito.verify(bookRepository).existsById(addBookCommand.id());
  }

  @Test
  public void shouldThrowsExceptionWhenBookIdAlreadyExistsInTheSystem() {

    final var faker = new Faker();
    final var addBookCommand = AddBookCommand.builder()
        .id(UUID.randomUUID())
        .title(faker.book().title())
        .author(faker.book().author())
        .genre(faker.book().genre())
        .publisher(faker.book().publisher())
        .publishedOn(LocalDate.now().minusMonths(1))
        .build();

    Mockito.doReturn(true)
        .when(bookRepository)
        .existsById(addBookCommand.id());

    assertThrows(
        BookAlreadyExistsException.class,
        () -> addBookUseCase.execute(addBookCommand),
        "Book with ID " + addBookCommand.id() + " already exists in the system"
    );

    Mockito.verify(bookRepository).existsById(addBookCommand.id());
    Mockito.verify(bookRepository, Mockito.never()).save(any());
  }
}
