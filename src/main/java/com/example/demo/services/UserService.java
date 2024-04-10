package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.User;

public interface UserService {
    List<User> getAllUsers();
    User addUser(User user);
    User getUserById(String userId);
    User updateUserScore(String userId, int score);
    void deleteUser(String userId);
}
