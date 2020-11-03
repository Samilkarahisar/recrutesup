package com.polytech.recrutesup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.polytech.recrutesup.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query("Select s "
	     + "from Student s "
		 + "inner join User u on u.id = s.user.id "
	     + "where u.mailAddress = :mailAddress ")
	Student findOneByMailAddress(@Param("mailAddress") String mailAddress);
}
