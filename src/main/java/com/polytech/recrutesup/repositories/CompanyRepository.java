package com.polytech.recrutesup.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.User;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Override
	Optional<Company> findById(Long idCompany);

	Optional<Company> findByName(String name);

	Optional<Company> findByEmployeesContains(User employee);

}
