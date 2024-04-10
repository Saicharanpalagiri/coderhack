package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    private List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    private ResponseEntity<User> addUser(@RequestBody User user){
        User createduser= userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createduser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId){
        User user=userService.getUserById(userId);
        if(user!=null)
        return ResponseEntity.ok(user);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserScore(@PathVariable String userId,@RequestParam int score){
        User user=userService.updateUserScore(userId, score);
        if(user!=null)
        return ResponseEntity.ok(user);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId){
        System.out.println("entered delete Mapping");
        userService.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
