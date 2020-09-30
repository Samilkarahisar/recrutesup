package com.polytech.recrutesup.services;


import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> listAll(){
        return userRepository.findAll();
    }
    public void save(User user){
        userRepository.save(user);
    }
    public Optional<User> get(Long id){
        return userRepository.findById(id);
    }
    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
