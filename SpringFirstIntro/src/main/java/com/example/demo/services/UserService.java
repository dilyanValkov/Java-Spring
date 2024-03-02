package com.example.demo.services;

import com.example.demo.models.User;

public interface UserService {

    void register (String userName, int age);

    User findByUserName(String username);

}
