Feature: Happy path scenario

  Scenario: Creating some new books, updating the publisher correctly and then getting them

    Given A user wants to add some new books to the catalog
    When User adds a new book with the following data: id "a2dd03db-6690-410f-9265-b3d2316c59d5", title "The best book", author "Mike Tyson", genre "Fiction", publisher "Book Publishing Company" and published on "2021-02-24"
    And User updates the book publisher correctly previously added with the following new value "Roberto Book Publishing Company"
    And User adds a new book with the following data: id "441bd53e-6e34-403c-b13e-a2d192df00b6", title "New book", author "Lionel Messi", genre "Novel", publisher "Roberto Book Publishing Company" and published on "2020-05-23"
    Then User gets a book previously added correctly
    And User gets the entire books catalog
