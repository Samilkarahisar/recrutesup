package com.polytech.recrutesup.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.CreateStudentDTO;
import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.entities.Student;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mappers.StudentMapper;
import com.polytech.recrutesup.mappers.UserMapper;
import com.polytech.recrutesup.repositories.StudentRepository;
import com.polytech.recrutesup.repositories.UserRepository;
import com.polytech.recrutesup.services.StudentService;
import com.polytech.recrutesup.services.dto.StudentServiceDTO;

@Service
public class StudentServiceImpl implements StudentService, StudentServiceDTO {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public Student findOne(Long id) {
		Optional<Student> student = this.studentRepository.findById(id);
		if(!student.isPresent()) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.STUDENT_UNKNOWN);
		}
		return student.get();
	}

	@Override
	public StudentDTO createStudent(CreateStudentDTO createStudentDTO) {
		
		// On vérifie que l'étudiant n'existe pas en BDD, si c'est le cas on retourne une exception
		Student student = studentRepository.findOneByMailAddress(createStudentDTO.getMailAddress());
		if(student != null) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.STUDENT_ALREADY_CREATED);
		}
		
		// On récupère les objets User et Student à sauvegarder depuis l'objet CreateStudentDTO
		User user = new User();
		user.setFirstname(createStudentDTO.getFirstname());
		user.setLastname(createStudentDTO.getLastname().toUpperCase());
		user.setMailAddress(createStudentDTO.getMailAddress());
		user.setPhoneNumber(createStudentDTO.getPhoneNumber());
		//TODO : generate password using BEncryption
		user.setPassword("mot de passe");
		student = this.studentMapper.createStudentDTOToStudent(createStudentDTO, user);
		student.setState(EWorkflowState.ENREGISTRE);
		
		// Sauvegarde en BDD
		user = userRepository.save(user);
		student = studentRepository.save(student);
		
		// On retourne le StudentDTO au front
		return studentMapper.studentToStudentDTO(student);
	}

	@Override
	public StudentDTO getStudent(@NotNull Long idStudent) {
		
		// On récupère l'étudiant en BDD si il existe
		Student student = this.findOne(idStudent);
		
		// On retourne le StudentDTO au front
		return studentMapper.studentToStudentDTO(student);
	}

	@Override
	public List<StudentDTO> getAllStudents() {
		
		List<StudentDTO> listStudentDTO = new ArrayList<>();
		
		List<Student> listStudent = this.studentRepository.findAll();
		listStudent.forEach(student -> listStudentDTO.add(studentMapper.studentToStudentDTO(student)));

		return listStudentDTO;
	}
	
}
