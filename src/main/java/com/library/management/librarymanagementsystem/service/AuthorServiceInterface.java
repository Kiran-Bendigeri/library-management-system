package com.library.management.librarymanagementsystem.service;

import com.library.management.librarymanagementsystem.dto_models.AuthorDto;
import com.library.management.librarymanagementsystem.entity_models.AuthorEntity;

public interface AuthorServiceInterface {
    AuthorEntity saveOrGetAuthor(AuthorDto authorDto);
    AuthorEntity updateAuthorById(AuthorDto authorDto);
}
