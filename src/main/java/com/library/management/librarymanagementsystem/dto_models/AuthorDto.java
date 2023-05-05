package com.library.management.librarymanagementsystem.dto_models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.library.management.librarymanagementsystem.entity_models.BookEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorDto {

    private Integer authorId;

    @NotNull(message = "Name Cannot be null")
    @NotEmpty(message = "Name Cannot be empty")
    private String authorName;

    @NotNull(message = "Phone number cannot be null")
    @NotEmpty(message = "Phone number cannot be empty")
    @Size(min = 10, max = 10, message = "Phone number should be 10 digits")
    private Long phno;

    @Email
    @Size(max = 30, message = "email length should be below 30 characters")
    private String email;

    private List<BookEntity> listOfBooks;

    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private LocalDateTime createdOn;

    @DateTimeFormat(pattern = "dd-MM-YYYY")
    private LocalDateTime updatedOn;

}
