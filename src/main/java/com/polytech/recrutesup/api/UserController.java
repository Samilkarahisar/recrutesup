package com.polytech.recrutesup.api;

import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.services.impl.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping(value = "/{id}", produces = "application/json")
    public String getUser(@PathVariable Long id) {
        return userService.get(id).get().getFirstname();
    }
    @GetMapping(value = "/")
    public List<User> list(){
        return userService.listAll();
    }
}
