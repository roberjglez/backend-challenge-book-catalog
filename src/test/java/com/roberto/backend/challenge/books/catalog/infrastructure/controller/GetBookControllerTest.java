package com.roberto.backend.challenge.books.catalog.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBookQuery;
import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBookUseCase;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class GetBookControllerTest {

  @Mock
  private GetBookUseCase getBookUseCase;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    final GetBookController getBookController = new GetBookController(getBookUseCase);
    mockMvc = MockMvcBuilders.standaloneSetup(getBookController).build();
  }

  @Test
  void shouldGetBookCorrectly() throws Exception {

    final var bookId = UUID.randomUUID();

    mockMvc.perform(get("/books-catalog/{bookId}", bookId)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    Mockito.verify(getBookUseCase).get(any(GetBookQuery.class));
  }

  @Test
  void shouldThrowsNotFoundIfBookIdPathVariableIsNotIndicated() throws Exception {

    mockMvc.perform(get("/books-catalog")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    Mockito.verifyNoInteractions(getBookUseCase);
  }
}
