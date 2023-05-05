package com.library.management.librarymanagementsystem.service;


import com.library.management.librarymanagementsystem.dto_models.BookDto;

import java.util.List;

public interface BookServiceInterface {

    BookDto save(BookDto book);

    List<BookDto> getAllBooks();

    BookDto getBookById(Integer id);

    BookDto updateBookById(BookDto book);

    Boolean deleteBookById(Integer book);
}
