package com.library.management.librarymanagementsystem.repository;

import com.library.management.librarymanagementsystem.entity_models.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
