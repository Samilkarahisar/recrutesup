package com.polytech.recrutesup.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.payload.request.CreateStudentRequest;
import com.polytech.recrutesup.services.dto.StudentServiceDTO;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("student")
public class StudentController {
	
	@Autowired
	private StudentServiceDTO studentServiceDTO;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ADMIN') or hasRole('COMPANY')")
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		return new ResponseEntity<>(this.studentServiceDTO.getAllStudents(), HttpStatus.OK);
	}
	
	@GetMapping("/{idUser}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable Long idUser) {
		return new ResponseEntity<>(this.studentServiceDTO.getStudent(idUser), HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<StudentDTO> createStudent(@RequestBody CreateStudentRequest createStudentDTO) {
		return new ResponseEntity<>(this.studentServiceDTO.createStudent(createStudentDTO), HttpStatus.CREATED);
	}
	
	@PatchMapping("/{idUser}")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long idUser, @RequestBody CreateStudentRequest studentDTO) {
		return new ResponseEntity<>(this.studentServiceDTO.updateStudent(idUser, studentDTO), HttpStatus.OK);
	}

}
