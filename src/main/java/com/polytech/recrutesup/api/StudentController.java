package com.polytech.recrutesup.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polytech.recrutesup.dto.CreateStudentDTO;
import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.services.dto.StudentServiceDTO;

@RestController
@RequestMapping("student")
public class StudentController {
	
	@Autowired
	private StudentServiceDTO studentServiceDTO;
	
	@GetMapping("/all")
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		return new ResponseEntity<>(this.studentServiceDTO.getAllStudents(), HttpStatus.OK);
	}
	
	@GetMapping("/{idStudent}")
	public ResponseEntity<StudentDTO> getStudent(@PathVariable Long idStudent) {
		return new ResponseEntity<>(this.studentServiceDTO.getStudent(idStudent), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<StudentDTO> createStudent(@RequestBody CreateStudentDTO createStudentDTO) {
		return new ResponseEntity<>(this.studentServiceDTO.createStudent(createStudentDTO), HttpStatus.OK);
	}

}
