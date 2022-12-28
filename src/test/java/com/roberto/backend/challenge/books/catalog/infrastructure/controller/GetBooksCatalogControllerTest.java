package com.roberto.backend.challenge.books.catalog.infrastructure.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBooksCatalogUseCase;
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
public class GetBooksCatalogControllerTest {

  @Mock
  private GetBooksCatalogUseCase getBooksCatalogUseCase;

  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    final GetBooksCatalogController getBooksCatalogController = new GetBooksCatalogController(getBooksCatalogUseCase);
    mockMvc = MockMvcBuilders.standaloneSetup(getBooksCatalogController).build();
  }

  @Test
  void shouldGetBooksCatalogCorrectly() throws Exception {

    mockMvc.perform(get("/books-catalog")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    Mockito.verify(getBooksCatalogUseCase).get();
  }
}
