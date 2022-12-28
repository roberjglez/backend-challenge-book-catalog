package com.roberto.backend.challenge.books.catalog.infrastructure.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.roberto.backend.challenge.books.catalog.application.use_case.update.UpdateBookPublisherUseCase;
import com.roberto.backend.challenge.books.catalog.infrastructure.controller.UpdateBookPublisherController.UpdateBookPublisherRequest;
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
public class UpdateBookPublisherControllerTest {

  @Mock
  private UpdateBookPublisherUseCase updateBookPublisherUseCase;

  private MockMvc mockMvc;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    final UpdateBookPublisherController updateBookPublisherController = new UpdateBookPublisherController(updateBookPublisherUseCase);
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    JacksonTester.initFields(this, objectMapper);
    mockMvc = MockMvcBuilders.standaloneSetup(updateBookPublisherController).build();
  }

  @Test
  void shouldUpdateBookPublisherCorrectly() throws Exception {

    final var faker = new Faker();
    final var updateBookPublisherRequest = UpdateBookPublisherRequest.builder()
        .bookId(UUID.randomUUID())
        .publisher(faker.book().publisher())
        .build();

    mockMvc.perform(patch("/books-catalog")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateBookPublisherRequest)))
        .andExpect(status().isOk());
  }

  @Test
  void shouldThrowsBadRequestIfSomeOfTheNotNullRequestFieldsAreNotIndicated() throws Exception {

    final var updateBookPublisherRequest = UpdateBookPublisherRequest.builder()
        .bookId(UUID.randomUUID())
        .build();

    mockMvc.perform(patch("/books-catalog")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateBookPublisherRequest)))
        .andExpect(status().isBadRequest());

    Mockito.verify(updateBookPublisherUseCase, Mockito.never()).update(any());
  }
}
