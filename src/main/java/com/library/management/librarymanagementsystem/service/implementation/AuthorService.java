package com.library.management.librarymanagementsystem.service.implementation;

import com.library.management.librarymanagementsystem.dto_models.AuthorDto;
import com.library.management.librarymanagementsystem.entity_models.AuthorEntity;
import com.library.management.librarymanagementsystem.repository.AuthorRepository;
import com.library.management.librarymanagementsystem.service.AuthorServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService implements AuthorServiceInterface {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorEntity saveOrGetAuthor(AuthorDto author) {
        Optional<AuthorEntity> optional = authorRepository.findByEmail(author.getEmail());
        if(!optional.isPresent()){
            AuthorEntity authorEntity = AuthorEntity.builder()
                                                    .authorName(author.getAuthorName())
                                                    .phno(author.getPhno())
                                                    .email(author.getEmail())
                                                    .build();
            return authorRepository.save(authorEntity);
        }else
            return optional.get();
    }

    @Override
    public AuthorEntity updateAuthorById(AuthorDto authorDto) {
        Optional<AuthorEntity> optional = authorRepository.findByAuthorIdAndEmail(authorDto.getAuthorId(),
                                                                            authorDto.getEmail());
        AuthorEntity authorEntity = null;
        if(optional.isPresent()){
            authorEntity = optional.get();
            authorEntity.setAuthorName(authorDto.getAuthorName());
            authorEntity.setEmail(authorDto.getEmail());
            authorEntity.setPhno(authorDto.getPhno());
            return authorEntity;
        }else throw new RuntimeException("Author not found with this id");
    }


}
