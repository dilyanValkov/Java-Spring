package com.example.demo.services;

import com.example.demo.models.Account;
import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String userName, int age) {
        if (userName.isBlank() || age < 18){
            throw new RuntimeException("Validation failed!");
        }

        Optional<User> byUsername = userRepository.findByUsername(userName);
        if (byUsername.isPresent()){
            throw new RuntimeException("Username is already in use");
        }

        Account account = new Account();
        User user = new User(userName, age, account);
        this.userRepository.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return this.userRepository.findByUsername(username).get();
    }
}
