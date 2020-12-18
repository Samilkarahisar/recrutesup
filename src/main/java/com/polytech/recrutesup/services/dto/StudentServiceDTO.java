package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.payload.request.CreateStudentRequest;
import com.polytech.recrutesup.payload.request.LoginRequest;

public interface StudentServiceDTO {

	StudentDTO createStudent(@NotNull @Valid CreateStudentRequest createStudentDTO);
	
	StudentDTO getStudent(@NotNull Long idUser);
	
	List<StudentDTO> getAllStudents();
	
	StudentDTO updateStudent(@PathVariable Long idUser, @RequestBody CreateStudentRequest studentDTO);
	
	StudentDTO changePassword(@PathVariable Long idUser, @RequestBody LoginRequest loginRequest);
}
