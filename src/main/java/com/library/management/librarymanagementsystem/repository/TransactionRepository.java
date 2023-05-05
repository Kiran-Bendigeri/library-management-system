package com.library.management.librarymanagementsystem.repository;

import com.library.management.librarymanagementsystem.entity_models.BookEntity;
import com.library.management.librarymanagementsystem.entity_models.TransactionEntity;
import com.library.management.librarymanagementsystem.entity_models.UserEntity;
import com.library.management.librarymanagementsystem.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findByBookAndUserAndTransactionTypeOrderByIdDesc(BookEntity bookEntity, UserEntity userEntity, TransactionType transactionType);

}
