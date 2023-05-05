package com.library.management.librarymanagementsystem.dto_models;

import com.library.management.librarymanagementsystem.entity_models.BookEntity;
import com.library.management.librarymanagementsystem.entity_models.UserEntity;
import com.library.management.librarymanagementsystem.enums.TransactionStatus;
import com.library.management.librarymanagementsystem.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    private Integer id;
    private String transactionId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private UserDto userDto;
    private BookDto bookDto;
    private Integer fine;
    private TransactionStatus transactionStatus;
    private TransactionType transactionType;

}
