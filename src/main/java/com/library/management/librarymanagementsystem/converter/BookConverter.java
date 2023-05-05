package com.library.management.librarymanagementsystem.converter;

import com.library.management.librarymanagementsystem.dto_models.BookDto;
import com.library.management.librarymanagementsystem.entity_models.BookEntity;
import org.springframework.stereotype.Component;


@Component
public class BookConverter {

    private final AuthorConverter authorConverter;
    private final UserConverter userConverter;

    public BookConverter(AuthorConverter authorConverter,
                         UserConverter userConverter){
        this.authorConverter = authorConverter;
        this.userConverter = userConverter;
    }

    public BookEntity dtoToEntity(BookDto bookDto){
        BookEntity bookEntity= BookEntity.builder()
                .bookName(bookDto.getBookName())
                .createdOn(bookDto.getCreatedOn())
                .updatedOn(bookDto.getUpdatedOn())
                .type(bookDto.getType())
                .bookId(bookDto.getBookId())
                .cost(bookDto.getCost())
                .author(authorConverter.dtoToEntity(bookDto.getAuthor()))
                .build();

        if(bookDto.getUserDto() !=null){
            bookEntity.setUser(userConverter.dtoToEntity(bookDto.getUserDto()));
        }
        return bookEntity;
    }

    public BookDto entityToDto(BookEntity bookEntity){
        BookDto bookDto = BookDto.builder().bookName(bookEntity.getBookName())
                .bookId(bookEntity.getBookId())
                .cost(bookEntity.getCost())
                .type(bookEntity.getType())
                .createdOn(bookEntity.getCreatedOn())
                .updatedOn(bookEntity.getUpdatedOn())
                .author(authorConverter.entityToDto(bookEntity.getAuthor()))
                .build();
        if(bookEntity.getUser() !=null){
            bookDto.setUserDto(userConverter.entityToDto(bookEntity.getUser()));
        }
        return bookDto;
    }
}
