package com.polytech.recrutesup.controllers;

import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users-rest")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/{id}", produces = "application/json")
    public String getUser(@PathVariable Long id) {
        return userRepository.getOne(id).get_name();
    }

}