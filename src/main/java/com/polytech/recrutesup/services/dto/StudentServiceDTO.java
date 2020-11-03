package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.CreateStudentDTO;
import com.polytech.recrutesup.dto.StudentDTO;

public interface StudentServiceDTO {

	StudentDTO createStudent(@NotNull @Valid CreateStudentDTO createDossierDTO);
	
	StudentDTO getStudent(@NotNull Long idStudent);
	
	List<StudentDTO> getAllStudents();
}
