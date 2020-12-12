package com.polytech.recrutesup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.polytech.recrutesup.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	@Query("Select a "
		 + "from Admin a "
		 + "where a.user.id = :idUser ")
	Admin findByIdUser(@Param("idUser") Long idUser);
}
