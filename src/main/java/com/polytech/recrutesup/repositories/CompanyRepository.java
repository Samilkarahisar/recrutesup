package com.polytech.recrutesup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.polytech.recrutesup.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

	@Query("Select c "
		 + "from Company c "
	     + "where c.name = :name")
	Company findOneByName(@Param("name") String name);
}
