package com.library.management.librarymanagementsystem.controllers;

import com.library.management.librarymanagementsystem.dto_models.UserDto;
import com.library.management.librarymanagementsystem.service.implementation.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public UserDto save(@Valid @RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @GetMapping("/get/All")
    public List<UserDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/get")
    public ResponseEntity<UserDto> getUserById(@Param("id") Integer id){
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
    }
}
