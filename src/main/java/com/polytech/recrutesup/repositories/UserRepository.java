package com.polytech.recrutesup.repositories;

import com.polytech.recrutesup.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByMailAddress(String mailAddress);
    
    Boolean existsByMailAddress(String mailAddress);

}
