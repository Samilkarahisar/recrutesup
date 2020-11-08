package com.polytech.recrutesup.repositories;

import com.polytech.recrutesup.entities.Role;
import com.polytech.recrutesup.entities.reference.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole role);
}
