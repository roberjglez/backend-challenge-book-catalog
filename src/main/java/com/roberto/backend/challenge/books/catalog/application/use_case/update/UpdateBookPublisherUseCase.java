package com.roberto.backend.challenge.books.catalog.application.use_case.update;

import com.roberto.backend.challenge.books.catalog.domain.exception.BookNotFoundException;
import com.roberto.backend.challenge.books.catalog.domain.exception.PublisherUpdateNotAllowedException;
import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Publisher;
import com.roberto.backend.challenge.books.catalog.domain.port.repository.BookRepository;
import java.time.LocalDate;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateBookPublisherUseCase {

  private final LocalDate MAX_DATE_TO_UPDATE = LocalDate.now().minusYears(5);
  private final BookRepository bookRepository;

  @Transactional
  public void update(final UpdateBookPublisherCommand command) {
    final UUID bookId = command.bookId();
    final Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new BookNotFoundException(bookId));

    if (book.getPublishedOn().value().isBefore(MAX_DATE_TO_UPDATE)) {
      throw new PublisherUpdateNotAllowedException(bookId);
    }

    book.updatePublisher(new Publisher(command.publisher()));

    bookRepository.save(book);
  }

}
