package com.example.demo.services;

import com.example.demo.dto.request.UserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class UserRedisServices {


    private static final String KEY = "user";

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;


    @Autowired
    public UserRedisServices(RedisTemplate<String, Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init(){
        hashOperations = redisTemplate.opsForHash();
    }


    public void add(User user) {
        hashOperations.put(KEY, user.getId(), (Object) user);
    }

    public void delete(Long id) {
        hashOperations.delete(KEY, id);
    }

    public UserResponse findUserById(Long id) {
        User user = (User) hashOperations.get(KEY, id);
        return convertModelToResponse(user);
    }

    public Map<Object, Object> findAllUsers(){
        return hashOperations.entries(KEY);
    }

    public UserResponse convertModelToResponse(User user){
        return UserResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .build();
    }

    public void update(Long id, User user) {
        hashOperations.put(KEY, id, (Object) user);
    }
}
