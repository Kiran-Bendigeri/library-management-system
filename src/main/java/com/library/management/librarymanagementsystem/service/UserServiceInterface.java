package com.library.management.librarymanagementsystem.service;


import com.library.management.librarymanagementsystem.dto_models.UserDto;

import java.util.List;

public interface UserServiceInterface {

    UserDto save(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Integer id);
}
