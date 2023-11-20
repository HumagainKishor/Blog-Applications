package com.example.controller;

import com.example.payloads.UserDto;
import com.example.service.UserService;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

import java.util.List;


@Controller("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Post("/create")
    public UserDto createUser(@Valid @Body UserDto userDto){
        return this.userService.createUser(userDto);
    }

    @Put("/update/{userId}")
    public UserDto updateUser(@Valid @Body UserDto userDto, @PathVariable Long userId){
        return this.userService.updateUser(userDto,userId);
    }
    @Get("/get/{userId}")
    public UserDto getUserById(Long userId){
        return this.userService.getUserById(userId);
    }
    @Get("/getall")
    public List<UserDto> getAllUser(){
        return this.userService.getAllUser();
    }
    @Delete("/delete/{userId}")
    public void deleteUser(@PathVariable  Long userId){
        this.userService.deleteUser(userId);
    }

}
