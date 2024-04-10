package com.example.demo.entities;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="users")
public class User {
    public User(String userId, String userName, int score, Set<String> badges) {
        //TODO Auto-generated constructor stub
        this.userId = userId;
        this.userName = userName;
        this.score = score;
        this.badges = badges;
    }
    @Id
    private String userId;
    private String userName;
    private int score;
    private Set<String> badges;
}
