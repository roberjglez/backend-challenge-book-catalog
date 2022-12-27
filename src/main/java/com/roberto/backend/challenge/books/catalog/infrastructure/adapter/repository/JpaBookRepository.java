package com.roberto.backend.challenge.books.catalog.infrastructure.adapter.repository;

import com.roberto.backend.challenge.books.catalog.infrastructure.adapter.repository.model.JpaBook;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBookRepository extends JpaRepository<JpaBook, UUID> {

}
