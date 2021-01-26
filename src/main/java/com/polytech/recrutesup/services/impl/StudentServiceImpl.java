package com.polytech.recrutesup.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.entities.Role;
import com.polytech.recrutesup.entities.Student;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.entities.reference.ERole;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mail.service.MailService;
import com.polytech.recrutesup.mappers.StudentMapper;
import com.polytech.recrutesup.payload.request.CreateStudentRequest;
import com.polytech.recrutesup.payload.request.LoginRequest;
import com.polytech.recrutesup.repositories.AdminRepository;
import com.polytech.recrutesup.repositories.RoleRepository;
import com.polytech.recrutesup.repositories.StudentRepository;
import com.polytech.recrutesup.security.services.UserDetailsImpl;
import com.polytech.recrutesup.services.StudentService;
import com.polytech.recrutesup.services.dto.StudentServiceDTO;
import com.polytech.recrutesup.utils.RandomStringUtils;
import com.polytech.recrutesup.utils.WorkflowStateUtils;

@Service
public class StudentServiceImpl implements StudentService, StudentServiceDTO {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Student findOne(Long idUser) {
		Student student = this.studentRepository.findByIdUser(idUser);
		if(student == null) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.STUDENT_UNKNOWN);
		}
		return student;
	}

	@Override
	public StudentDTO createStudent(CreateStudentRequest createStudentDTO) {
		
		// On vérifie que l'étudiant n'existe pas en BDD, si c'est le cas on retourne une exception
		Student student = studentRepository.findOneByMailAddress(createStudentDTO.getMailAddress());
		if(student != null) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.STUDENT_ALREADY_CREATED);
		}
		
		// On crée l'objet Student et toutes ses dépendances à sauvegarder depuis l'objet CreateStudentDTO
		Role role = roleRepository.findByName(ERole.ROLE_STUDENT).orElseThrow(()-> new RecruteSupApplicationException(RecruteSupErrorType.ROLE_STUDENT_UNKNOWN));
		
		User user = new User();
		user.setFirstname(createStudentDTO.getFirstname().trim());
		user.setLastname(createStudentDTO.getLastname().trim().toUpperCase());
		user.setMailAddress(createStudentDTO.getMailAddress().trim());
		user.setPhoneNumber(createStudentDTO.getPhoneNumber());
		user.setRole(role);
		
		// Génération d'un mot de passe aléatoire de 16 caractères
		RandomStringUtils randomString = new RandomStringUtils(16);
		String mdp = randomString.nextString();
		user.setPassword(passwordEncoder.encode(mdp));
		
		student = this.studentMapper.createStudentRequestToStudent(createStudentDTO, user);
		student.setState(EWorkflowState.ENREGISTRE);
		
		// Sauvegarde en BDD
		student = studentRepository.save(student);	
		
		mailService.sendEmailCreationStudentProfil(user, mdp);
		
		// On retourne le StudentDTO au front
		return studentMapper.studentToStudentDTO(student);
	}

	@Override
	public StudentDTO getStudent(@NotNull Long idUser) {
		
		// On récupère l'étudiant en BDD si il existe
		Student student = this.findOne(idUser);
		
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

	@Override
	public StudentDTO updateStudent(CreateStudentRequest studentDTO) {
		Long idUser = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		Student student = this.findOne(idUser);
		this.studentMapper.updateStudentFromCreateStudentRequest(studentDTO, student);
		student = studentRepository.save(student);
		
		return studentMapper.studentToStudentDTO(student);
	}
	
	@Override
	public StudentDTO updateStateStudent(@NotNull Long idUser, @NotNull String currentState, @NotNull String nextState) {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long idUserConnected = userDetails.getId();
		
		boolean acceptable = false;
		if(this.adminRepository.findByIdUser(idUserConnected) != null) {
			acceptable = WorkflowStateUtils.isNextStateAcceptable(ERole.ROLE_ADMIN, currentState, nextState);
		} else {
			if(idUser != idUserConnected) {
				throw new RecruteSupApplicationException(RecruteSupErrorType.UPDATE_STATE_STUDENT_INVALID);
			}
			acceptable = WorkflowStateUtils.isNextStateAcceptable(ERole.ROLE_STUDENT, currentState, nextState);
		}
		
		if(!acceptable) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.UPDATE_STATE_STUDENT_INVALID);
		}
		
		Student student = this.findOne(idUser);
		if(!currentState.equals(student.getState().toString())) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.STATE_STUDENT_INCORRECT);
		}
		
		student.setState(EWorkflowState.valueOf(nextState));
		
		student = this.studentRepository.save(student);
		return studentMapper.studentToStudentDTO(student);
	}

	@Override
	public StudentDTO changePassword(LoginRequest loginRequest) {
		Long idUser = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		Student student = this.findOne(idUser);
		
		student.getUser().setPassword(passwordEncoder.encode(loginRequest.getPassword()));
		student = studentRepository.save(student);	
		mailService.sendEmailConfirmationChangePassword(student.getUser());
		
		return studentMapper.studentToStudentDTO(student);
	}
}
