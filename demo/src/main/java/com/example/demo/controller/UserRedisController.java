package com.example.demo.controller;

import com.example.demo.dto.response.MessageResponse;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.services.UserRedisServices;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/redis/user")
@Slf4j
public class UserRedisController {

    @Autowired
    private UserRedisServices userRedisServices;

    @GetMapping
    public Map<Object, Object> findAllUser(){
        return userRedisServices.findAllUsers();
    }


    @GetMapping("/{id}")
    public UserResponse findById(@PathVariable("id") Long id){
        return userRedisServices.findUserById(id);
    }

    @PostMapping
    public MessageResponse saveUser(@Valid @RequestBody User user){
         userRedisServices.add(user);
         return MessageResponse.builder()
                 .message("data berhasil di tambahkan")
                 .build();
    }

    @PutMapping("/{id}")
    public MessageResponse updateUser(@PathVariable("id") Long id,@Valid @RequestBody User user){
         userRedisServices.update(id, user);
        return MessageResponse.builder()
                .message("data berhasil di edit")
                .build();
    }

    @DeleteMapping("/{id}")
    public MessageResponse deleteUser(@PathVariable("id") long id){
         userRedisServices.delete(id);
        return MessageResponse.builder()
                .message("data berhasil di hapus")
                .build();
    }



}
