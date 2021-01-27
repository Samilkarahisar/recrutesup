package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.RequestBody;

import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.payload.request.CreateStudentRequest;
import com.polytech.recrutesup.payload.request.LoginRequest;

public interface StudentServiceDTO {

	StudentDTO createStudent(@NotNull @Valid CreateStudentRequest createStudentDTO);
	
	StudentDTO getStudent(@NotNull Long idUser);
	
	List<StudentDTO> getAllStudents();
	
	StudentDTO updateStudent(@RequestBody CreateStudentRequest studentDTO);
	
	StudentDTO changePassword(@RequestBody LoginRequest loginRequest);
	
	StudentDTO updateStateStudent(@NotNull Long idUser, @NotNull String currentState, @NotNull String nextState);
}
