package com.library.management.librarymanagementsystem.converter;

import com.library.management.librarymanagementsystem.dto_models.UserDto;
import com.library.management.librarymanagementsystem.entity_models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity dtoToEntity(UserDto userDto){
        return UserEntity.builder().userName(userDto.getUserName())
                            .email(userDto.getEmail())
                            .phno(userDto.getPhno())
                            .build();
    }

    public UserDto entityToDto(UserEntity userEntity){
        return UserDto.builder().userId(userEntity.getUserId())
                        .userName(userEntity.getUserName())
                        .createdOn(userEntity.getCreatedOn())
                        .updatedOn(userEntity.getUpdatedOn())
                        .email(userEntity.getEmail())
                        .phno(userEntity.getPhno())
                        .build();
    }
}
