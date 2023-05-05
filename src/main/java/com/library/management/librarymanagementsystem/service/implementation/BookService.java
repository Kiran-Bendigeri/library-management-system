package com.library.management.librarymanagementsystem.service.implementation;

import com.library.management.librarymanagementsystem.converter.AuthorConverter;
import com.library.management.librarymanagementsystem.converter.BookConverter;
import com.library.management.librarymanagementsystem.dto_models.BookDto;
import com.library.management.librarymanagementsystem.entity_models.BookEntity;
import com.library.management.librarymanagementsystem.repository.BookRepository;
import com.library.management.librarymanagementsystem.service.BookServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;

    @Autowired
    private BookConverter bookConverter;

    @Autowired
    private AuthorConverter authorConverter;

    @Autowired
    private AuthorService authorService;

    public BookService(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @Override
    public BookDto save(BookDto book) {
        BookEntity bookEntity = bookConverter.dtoToEntity(book);
        bookEntity.setAuthor(authorService.saveOrGetAuthor(book.getAuthor()));
        return bookConverter.entityToDto(bookRepository.save(bookEntity));
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<BookEntity> books = bookRepository.findAll();
        if(books.isEmpty())
            throw new RuntimeException("No Books present");
        List<BookDto> allBook = new ArrayList<>();
        for(BookEntity book : books){
            allBook.add(bookConverter.entityToDto(book));
        }
        return allBook;
    }

    @Override
    public BookDto getBookById(Integer id){
        Optional<BookEntity> optional = bookRepository.findById(id);
        if(optional.isPresent()){
            return bookConverter.entityToDto(optional.get());
        }else throw new RuntimeException("Book not found with given id");
    }

    @Override
    public BookDto updateBookById(BookDto book) {
        Optional<BookEntity> optional = bookRepository.findById(book.getBookId());
        BookEntity bookEntity = null;
        if(optional.isPresent()){
            bookEntity = optional.get();
            bookEntity.setBookName(book.getBookName());
            bookEntity.setCost(book.getCost());
            bookEntity.setAuthor(authorService.updateAuthorById(book.getAuthor()));
            return bookConverter.entityToDto(bookRepository.save(bookEntity));
        }
        else throw new RuntimeException("Book not found");
    }

    @Override
    public Boolean deleteBookById(Integer id) {
        Optional<BookEntity> optional = bookRepository.findById(id);
        if(optional.isPresent()){
            bookRepository.deleteById(id);
            return true;
        }
        throw new RuntimeException("No Books present");
    }
}
