package com.example.demo.services;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        List<User> userList= userRepository.findAll();
        if(userList.size()!=0){
            return userList.stream()
            .sorted(Comparator.comparingInt(User::getScore).reversed())
            .collect(Collectors.toList());
        }
        return userList;
    }

    @Override
    public User addUser(User user) {
        // TODO Auto-generated method stub
        user.setScore(0);
        user.setBadges(Set.of());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        // TODO Auto-generated method stub
       return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User updateUserScore(String userId, int score) {
        // TODO Auto-generated method stub
        User user=userRepository.findById(userId).orElse(null);
        if(user!=null){
            user.setScore(score);
            updateBadges(user);
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
         userRepository.deleteById(userId);
    }
    
    public void updateBadges(User user){
        int score=user.getScore();
        Set<String> badges=user.getBadges();
        if(badges==null){
            badges=new HashSet<String>();
        }
        if(score>=1 && score<30){
            badges.add("Code Ninja");
        }else if(score>=30 && score<60){
            badges.add("Code Champ");
        }else if(score>=60 && score<=100){
            badges.add("Code Master");
        }

        if(badges.size()>3 || score<0 || score>100){
            throw new IllegalArgumentException("User cannot have more than three badges");
        }
        user.setBadges(badges);
    }
}
