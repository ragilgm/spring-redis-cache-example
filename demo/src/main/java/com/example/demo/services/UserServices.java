package com.example.demo.services;


import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServices {

    @Autowired
    private UserRepository userRepository;


    public List<UserResponse> listUser(){
        List<User> user = (List<User>) userRepository.findAll();
        return user
                .stream()
                .map(this::convertModeToResponse)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    public UserResponse findUserById(long id){
        return userRepository.findById(id).map(this::convertModeToResponse)
                .orElseThrow(()-> new Exception("user not found"));
    }

    public UserResponse saveUser(UserRequest request){
        User user = userRepository.save(
                User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .build()
        );
        return convertModeToResponse(user);
    }

    @SneakyThrows
    public Boolean deleteUser(long id){
        return userRepository.findById(id)
                .map(u-> {
                    userRepository.delete(u);
                    return Boolean.TRUE;
                }).orElseThrow(()-> new Exception("user not found"));
    }

    @SneakyThrows
    public UserResponse updateUser(Long id, UserRequest request){
        User user = userRepository.findById(id).map(updateUser-> {
            updateUser.setFirstName(request.getFirstName());
            updateUser.setLastName(request.getLastName());
            return userRepository.save(updateUser);
        }).orElseThrow(()-> new Exception("user not found"));
        return convertModeToResponse(user);
    }


    public UserResponse convertModeToResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }



}
