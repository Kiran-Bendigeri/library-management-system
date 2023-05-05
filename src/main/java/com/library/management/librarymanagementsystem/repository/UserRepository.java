package com.library.management.librarymanagementsystem.repository;

import com.library.management.librarymanagementsystem.entity_models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
