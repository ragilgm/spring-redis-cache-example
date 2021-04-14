package com.example.demo.controller;


import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.MessageResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.services.UserServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/vi/user")
@Slf4j
public class UserController {


    @Autowired
    private UserServices userServices;


    @GetMapping
    public List<UserResponse> findAllUser(){
        return userServices.listUser();
    }

    @GetMapping("/{id}")
    @Cacheable(value = "user", key = "#id")
    public UserResponse findById(@PathVariable("id") long id){
        log.info("getting user id "+id+" in database");
        return userServices.findUserById(id);
    }

    @PostMapping
    @CachePut(value="user" ,key = "#result.id")
    public UserResponse saveUser(@Valid @RequestBody UserRequest request){
        log.info("saving user to database");
        return userServices.saveUser(request);
    }

    @PutMapping("/{id}")
    @CachePut (value = "user", key = "#result.id")
    public UserResponse updateUser(@PathVariable("id") long id,@Valid @RequestBody UserRequest request){
        log.info("updating user to database");
        return userServices.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value="user",key = "#id")
    public MessageResponse deleteUser(@PathVariable("id") long id){
        log.info("deleting user in database");
        return userServices.deleteUser(id);
    }



}
