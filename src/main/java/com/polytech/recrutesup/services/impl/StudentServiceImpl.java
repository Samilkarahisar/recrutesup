package com.polytech.recrutesup.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.entities.Student;
import com.polytech.recrutesup.repositories.StudentRepository;
import com.polytech.recrutesup.services.StudentService;
import com.polytech.recrutesup.services.dto.StudentServiceDTO;

@Service
public class StudentServiceImpl implements StudentService, StudentServiceDTO {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public StudentDTO createStudentDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentDTO getStudentDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
