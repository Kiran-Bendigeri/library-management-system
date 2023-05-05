package com.library.management.librarymanagementsystem.service;


import com.library.management.librarymanagementsystem.dto_models.BookDto;

public interface TransactionServiceInterface {
    String issueBook(Integer bookId, Integer userId);
    String returnBook(Integer bookId, Integer userId);
}
