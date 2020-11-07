package com.polytech.recrutesup.security.services;

import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    //The username is the mailaddress -> cannot change method name cos it's implemented from userDetailsService
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String mailAddress) throws UsernameNotFoundException {
        User user = userRepository.findByMailAddress(mailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with mailAddress : " + mailAddress));

        return UserDetailsImpl.build(user);
    }

}
