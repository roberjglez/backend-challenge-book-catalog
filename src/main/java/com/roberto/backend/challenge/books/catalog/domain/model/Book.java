package com.roberto.backend.challenge.books.catalog.domain.model;

import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Author;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Genre;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Id;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Publisher;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.Title;
import com.roberto.backend.challenge.books.catalog.domain.model.value_objects.PublishedOn;
import lombok.Builder;

@Builder
public record Book (
    Id id,
    Title title,
    Author author,
    Genre genre,
    Publisher publisher,
    PublishedOn publishedOn) {
}
