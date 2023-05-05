package com.library.management.librarymanagementsystem.controllers;

import com.library.management.librarymanagementsystem.service.implementation.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/book/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/issue/{bookid}/{userid}")
    public String issueBook(@PathVariable("bookid") Integer bookId, @PathVariable("userid") Integer userId){
        return transactionService.issueBook(bookId, userId);
    }
    @GetMapping("/return")
    public String returnBook(@Param("bookId") Integer bookId, @Param("userId") Integer userId){
        return transactionService.returnBook(bookId, userId);
    }
}
