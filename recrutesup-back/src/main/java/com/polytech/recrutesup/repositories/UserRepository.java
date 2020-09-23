package com.polytech.recrutesup.repositories;

import com.polytech.recrutesup.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
