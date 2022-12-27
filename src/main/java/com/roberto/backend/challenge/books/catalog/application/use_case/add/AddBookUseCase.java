package com.roberto.backend.challenge.books.catalog.application.use_case.add;

import com.roberto.backend.challenge.books.catalog.domain.exception.BookAlreadyExistsException;
import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Author;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Genre;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Id;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.PublishedOn;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Publisher;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Title;
import com.roberto.backend.challenge.books.catalog.domain.port.repository.BookRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddBookUseCase {

  private final BookRepository bookRepository;

  @Transactional
  public void execute(final AddBookCommand command) {
    checkIfBookExists(command.id());

    bookRepository.save(Book.builder()
            .id(new Id(command.id()))
            .title(new Title(command.title()))
            .author(new Author(command.author()))
            .genre(new Genre(command.genre()))
            .publisher(new Publisher(command.publisher()))
            .publishedOn(new PublishedOn(command.publishedOn()))
            .build());
  }

  private void checkIfBookExists(final UUID bookId) {
    if (bookRepository.existsById(bookId)) {
      throw new BookAlreadyExistsException(bookId);
    }
  }
}
