package com.library.management.librarymanagementsystem.controllers;

import com.library.management.librarymanagementsystem.dto_models.BookDto;
import com.library.management.librarymanagementsystem.service.implementation.BookService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public ResponseEntity<BookDto> save(@Valid @RequestBody BookDto book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<BookDto>> getAllBooks(){
        List<BookDto> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/get")
    public BookDto getBookById(@Param("id") Integer id){
        return bookService.getBookById(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteBookById(@Param("id") Integer id){
        boolean bool = bookService.deleteBookById(id);
        if(bool)
            return new ResponseEntity<>("Book Deleted", HttpStatus.OK);
        else return new ResponseEntity<>("Book not found with given id", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<BookDto> updateBookById(@Valid @RequestBody BookDto bookDto){
        return null;
    }

}

