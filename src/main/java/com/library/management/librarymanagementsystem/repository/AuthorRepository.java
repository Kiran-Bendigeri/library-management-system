package com.library.management.librarymanagementsystem.repository;

import com.library.management.librarymanagementsystem.entity_models.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {

    Optional<AuthorEntity> findByEmail(String email);

    Optional<AuthorEntity> findByAuthorIdAndEmail(Integer id, String email);
}
