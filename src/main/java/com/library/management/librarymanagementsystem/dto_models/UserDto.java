package com.library.management.librarymanagementsystem.dto_models;

import jakarta.validation.constraints.Email;
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
public class UserDto {

    private Integer userId;
    @NotNull(message = "Username cannot be null")
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 1, max = 25, message = "Name should be -> 0<name<25")
    private String userName;
    private Long phno;
    @Email
    private String email;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<BookDto> listOfBooks;
    private List<TransactionDto> transaction;
}
