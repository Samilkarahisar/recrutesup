package com.polytech.recrutesup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.recrutesup.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
