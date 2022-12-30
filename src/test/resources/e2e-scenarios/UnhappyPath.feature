Feature: Unhappy path scenario

  Scenario: Creating a new book, trying to update its publisher incorrectly due to the publication date

    Given A user wants to add some new books to the catalog
    When User adds a new book with the following data: id "30d71c54-6d7b-4859-bd49-4b36fc3e9b3c", title "The best book", author "Mike Tyson", genre "Fiction", publisher "Book Publishing Company" and published on "2010-02-24"
    And User tries to update unsuccessfully the book publisher previously added with the following new value "Roberto Book Publishing Company"
