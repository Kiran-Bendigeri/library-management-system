package com.library.management.librarymanagementsystem.converter;

import com.library.management.librarymanagementsystem.dto_models.TransactionDto;
import com.library.management.librarymanagementsystem.entity_models.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private BookConverter bookConverter;

    public TransactionEntity dtoToEntity(TransactionDto transactionDto){
        return TransactionEntity.builder()
                                .id(transactionDto.getId())
                                .transactionId(transactionDto.getTransactionId())
                                .transactionType(transactionDto.getTransactionType())
                                .user(userConverter.dtoToEntity(transactionDto.getUserDto()))
                                .book(bookConverter.dtoToEntity(transactionDto.getBookDto()))
                                .updatedDate(transactionDto.getUpdatedDate())
                                .transactionStatus(transactionDto.getTransactionStatus())
                                .createdDate(transactionDto.getCreatedDate())
                                .build();
    }

    public TransactionDto entityToDto(TransactionEntity transactionEntity){
        return TransactionDto.builder()
                            .id(transactionEntity.getId())
                            .transactionId(transactionEntity.getTransactionId())
                            .transactionType(transactionEntity.getTransactionType())
                            .createdDate(transactionEntity.getCreatedDate())
                            .fine(transactionEntity.getFine())
                            .transactionStatus(transactionEntity.getTransactionStatus())
                            .userDto(userConverter.entityToDto(transactionEntity.getUser()))
                            .bookDto(bookConverter.entityToDto(transactionEntity.getBook()))
                            .updatedDate(transactionEntity.getUpdatedDate())
                            .build();
    }
}
