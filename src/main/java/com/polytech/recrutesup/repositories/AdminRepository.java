package com.polytech.recrutesup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.recrutesup.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

}
