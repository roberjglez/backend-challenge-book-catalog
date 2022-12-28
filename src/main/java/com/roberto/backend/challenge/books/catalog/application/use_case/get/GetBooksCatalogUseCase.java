package com.roberto.backend.challenge.books.catalog.application.use_case.get;

import com.roberto.backend.challenge.books.catalog.domain.model.Book;
import com.roberto.backend.challenge.books.catalog.domain.port.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetBooksCatalogUseCase {

  private final BookRepository bookRepository;

  public GetBooksCatalogResponse get() {
    final List<Book> books = bookRepository.findAll();

    return GetBooksCatalogResponse.builder()
        .books(books.stream()
            .map(GetBookResponse::from)
            .toList())
        .build();
  }
}
