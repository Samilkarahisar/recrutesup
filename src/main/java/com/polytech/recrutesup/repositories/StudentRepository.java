package com.polytech.recrutesup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.recrutesup.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
