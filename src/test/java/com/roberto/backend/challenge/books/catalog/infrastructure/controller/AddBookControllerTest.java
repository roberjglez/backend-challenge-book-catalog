package com.roberto.backend.challenge.books.catalog.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.roberto.backend.challenge.books.catalog.application.use_case.add.AddBookUseCase;
import com.roberto.backend.challenge.books.catalog.infrastructure.controller.AddBookController.AddBookRequest;
import java.time.LocalDate;
import java.util.UUID;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class AddBookControllerTest {

  @Mock
  private AddBookUseCase addBookUseCase;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    final AddBookController addBookController = new AddBookController(addBookUseCase);
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    JacksonTester.initFields(this, objectMapper);
    mockMvc = MockMvcBuilders.standaloneSetup(addBookController).build();
  }

  @Test
  void shouldAddBookCorrectly() throws Exception {

    final var faker = new Faker();
    final var addBookRequest = AddBookRequest.builder()
        .id(UUID.randomUUID())
        .title(faker.book().title())
        .author(faker.book().author())
        .genre(faker.book().genre())
        .publisher(faker.book().publisher())
        .publishedOn(LocalDate.now().minusMonths(1))
        .build();

    mockMvc.perform(post("/books-catalog/add-book")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(addBookRequest)))
        .andExpect(status().isCreated());
  }

  @Test
  void shouldThrowsBadRequestIfSomeOfTheNotNullRequestFieldsAreNotIndicated() throws Exception {

    final var faker = new Faker();
    final var addBookRequest = AddBookRequest.builder()
        .id(UUID.randomUUID())
        .title(faker.book().title())
        .author(faker.book().author())
        .genre(faker.book().genre())
        .publishedOn(LocalDate.now().minusMonths(1))
        .build();

    mockMvc.perform(post("/books-catalog/add-book")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(addBookRequest)))
        .andExpect(status().isBadRequest());

    Mockito.verify(addBookUseCase, Mockito.never()).execute(any());
  }
}
