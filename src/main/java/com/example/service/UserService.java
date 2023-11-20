package com.example.service;

import com.example.entity.Users;
import com.example.exceptions.ResourceNotFoundException;
import com.example.payloads.UserDto;
import com.example.repository.UserRepo;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;
@Singleton
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
    @Inject
    private ModelMapper modelMapper;

    public UserDto createUser(UserDto userDto) {
        Users user = this.dtoToUser(userDto);
        Users savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }
    @Transactional
    public UserDto updateUser(UserDto userDto, Long userId){
        Users user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        Users updatedUser = this.userRepo.save(user);

        return this.userToDto(updatedUser);

    }
    public UserDto getUserById(Long userId){
        Users user = this.userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User","Id",userId));

        return this.userToDto(user);
    }
    public List<UserDto> getAllUser(){
        List<Users> users = this.userRepo.findAll();
        List<UserDto> userDto = users.stream().map(user-> this.userToDto(user)).collect(Collectors.toList());
        return userDto;
    }
    public void deleteUser(Long userId){
        Users user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);

    }

    //Conversion of dto to user
    private Users dtoToUser(UserDto userDto){
        /*Users user = new Users();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        return user;*/
        Users user = this.modelMapper.map(userDto,Users.class);
        return user;
    }

    //conversion of user to dto
    private UserDto userToDto(Users user){
        /*UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setAbout(user.getAbout());
        userDto.setPassword(user.getPassword());
        return userDto;*/
        UserDto userDto = this.modelMapper.map(user,UserDto.class);
        return userDto;
    }



}
