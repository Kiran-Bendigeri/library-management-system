package com.library.management.librarymanagementsystem.service.implementation;

import com.library.management.librarymanagementsystem.converter.UserConverter;
import com.library.management.librarymanagementsystem.dto_models.UserDto;
import com.library.management.librarymanagementsystem.entity_models.UserEntity;
import com.library.management.librarymanagementsystem.repository.UserRepository;
import com.library.management.librarymanagementsystem.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDto save(UserDto userDto) {
        UserEntity userEntity = userRepository.save(userConverter.dtoToEntity(userDto));
        return userConverter.entityToDto(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        if(userEntities.isEmpty())
            throw new RuntimeException("No user found");
        List<UserDto> userDtos = new ArrayList<>();
        for(UserEntity userEntity : userEntities){
            userDtos.add(userConverter.entityToDto(userEntity));
        }
        return userDtos;
    }

    @Override
    public UserDto getUserById(Integer id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        if(optional.isPresent()){
            return userConverter.entityToDto(optional.get());
        }
        throw new RuntimeException("No User found with given id");
    }


}
