package com.roberto.backend.challenge.books.catalog.e2e_steps;

import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBookResponse;
import com.roberto.backend.challenge.books.catalog.application.use_case.get.GetBooksCatalogResponse;
import com.roberto.backend.challenge.books.catalog.infrastructure.controller.AddBookController.AddBookRequest;
import com.roberto.backend.challenge.books.catalog.infrastructure.controller.UpdateBookPublisherController.UpdateBookPublisherRequest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

public class E2ESteps {

  private static final String URL = "http://localhost:8080/books-catalog";
  private static String bookId;
  private static String newPublisher;

  @Given("A user wants to add some new books to the catalog")
  public void aUserWantsToAddSomeNewBooksToTheCatalog() {
  }

  @When("User adds a new book with the following data: id {string}, title {string}, author "
      + "{string}, genre {string}, publisher {string} and published on {string}")
  public static void userAddsANewBookWithTheFollowingDataIdTitleAuthorGenrePublisherAndPublishedOn(
      final String id,
      final String title,
      final String author,
      final String genre,
      final String publisher,
      final String publishedOn
  ) {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    bookId = id;

    final AddBookRequest addBookRequest = AddBookRequest.builder()
        .id(UUID.fromString(bookId))
        .title(title)
        .author(author)
        .genre(genre)
        .publisher(publisher)
        .publishedOn(LocalDate.parse(publishedOn, formatter))
        .build();

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    final HttpEntity<Object> entity = new HttpEntity<>(addBookRequest, headers);

    final ResponseEntity<Void> response = new RestTemplate().exchange(URL.concat("/add-book"), HttpMethod.POST, entity, Void.class);

    Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
    Assertions.assertNull(response.getBody());
  }

  @And("User updates the book publisher correctly previously added with the following new value {string}")
  public static void userUpdatesTheBookPublisherCorrectlyPreviouslyAddedWithTheFollowingNewValue(final String publisher) {

    newPublisher = publisher;
    final UpdateBookPublisherRequest updateBookPublisherRequest = UpdateBookPublisherRequest.builder()
        .bookId(UUID.fromString(bookId))
        .publisher(newPublisher)
        .build();

    final RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    final HttpEntity<Object> entity = new HttpEntity<>(updateBookPublisherRequest, headers);

    final ResponseEntity<Void> response = restTemplate.exchange(URL, HttpMethod.PATCH, entity, Void.class);

    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Assertions.assertNull(response.getBody());
  }

  @Then("User gets a book previously added correctly")
  public static void userGetsABookPreviouslyAddedCorrectly() {

    final ResponseEntity<GetBookResponse> response =
        new RestTemplate().exchange(URL.concat("/" + bookId), HttpMethod.GET, null, GetBookResponse.class);

    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    final GetBookResponse bookInformation = response.getBody();
    Assertions.assertEquals(bookId, bookInformation.id().toString());
    Assertions.assertEquals(newPublisher, bookInformation.publisher());
  }

  @And("User gets the entire books catalog")
  public static void userGetsTheEntireBooksCatalog() {

    final ResponseEntity<GetBooksCatalogResponse> response =
        new RestTemplate().exchange(URL, HttpMethod.GET, null, GetBooksCatalogResponse.class);

    Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    final GetBooksCatalogResponse booksCatalog = response.getBody();
    Assertions.assertEquals(2, booksCatalog.books().size());
  }

  @And("User tries to update unsuccessfully the book publisher previously added with the following new value {string}")
  public static void userTriesToUpdateUnsuccessfullyTheBookPublisherPreviouslyAddedWithTheFollowingNewValue(final String publisher) {

    final UpdateBookPublisherRequest updateBookPublisherRequest = UpdateBookPublisherRequest.builder()
        .bookId(UUID.fromString(bookId))
        .publisher(publisher)
        .build();

    final RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    final HttpEntity<Object> entity = new HttpEntity<>(updateBookPublisherRequest, headers);

    try{
      restTemplate.exchange(URL, HttpMethod.PATCH, entity, Void.class);
    } catch (final RuntimeException exc) {
      Assertions.assertEquals(((BadRequest) exc).getStatusCode(), HttpStatus.BAD_REQUEST);
    }
  }
}
