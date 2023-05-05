package com.library.management.librarymanagementsystem.converter;

import com.library.management.librarymanagementsystem.dto_models.AuthorDto;
import com.library.management.librarymanagementsystem.entity_models.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter {

    public AuthorDto entityToDto(AuthorEntity authorEntity){
        return AuthorDto.builder()
                        .authorId(authorEntity.getAuthorId())
                        .phno(authorEntity.getPhno())
                        .updatedOn(authorEntity.getUpdatedOn())
                        .email(authorEntity.getEmail())
                        .authorName(authorEntity.getAuthorName())
                        .createdOn(authorEntity.getCreatedOn())
                        .build();
    }

    public AuthorEntity dtoToEntity(AuthorDto authorDto){
        return AuthorEntity.builder()
                .phno(authorDto.getPhno())
                .updatedOn(authorDto.getUpdatedOn())
                .email(authorDto.getEmail())
                .authorName(authorDto.getAuthorName())
                .createdOn(authorDto.getCreatedOn())
                .build();
    }
}
