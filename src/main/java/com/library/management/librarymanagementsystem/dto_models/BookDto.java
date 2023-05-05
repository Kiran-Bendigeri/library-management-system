package com.library.management.librarymanagementsystem.dto_models;

import com.library.management.librarymanagementsystem.enums.Book;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Integer bookId;
    @NotNull(message="name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    @Size(max = 50)
    private String bookName;
    private Book type;
    private AuthorDto author;
    private Double cost;
    private UserDto userDto;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<TransactionDto> listOfTransaction;
}
