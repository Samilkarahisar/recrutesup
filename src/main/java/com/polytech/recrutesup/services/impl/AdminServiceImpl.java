package com.polytech.recrutesup.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.AdminDTO;
import com.polytech.recrutesup.entities.Admin;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.Student;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mail.service.MailService;
import com.polytech.recrutesup.mappers.AdminMapper;
import com.polytech.recrutesup.payload.request.LoginRequest;
import com.polytech.recrutesup.payload.request.MessageRequest;
import com.polytech.recrutesup.payload.request.UpdateAdminRequest;
import com.polytech.recrutesup.repositories.AdminRepository;
import com.polytech.recrutesup.repositories.CompanyRepository;
import com.polytech.recrutesup.repositories.StudentRepository;
import com.polytech.recrutesup.security.services.UserDetailsImpl;
import com.polytech.recrutesup.services.AdminService;
import com.polytech.recrutesup.services.dto.AdminServiceDTO;

@Service
public class AdminServiceImpl implements AdminService, AdminServiceDTO {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private MailService mailService;


	@Override
	public Admin findOne(Long idUser) {
		Admin admin = this.adminRepository.findByIdUser(idUser);
		if (admin == null) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.ADMIN_UNKNOWN);
		}
		return admin;
	}

	@Override
	public AdminDTO getAdmin(@NotNull Long idUser) {
		Admin admin = this.findOne(idUser);

		return adminMapper.adminToAdminDTO(admin);
	}

	@Override
	public List<AdminDTO> getAllAdmins() {
		List<AdminDTO> listAdminDTO = new ArrayList<>();

		List<Admin> listAdmin = this.adminRepository.findAll();
		listAdmin.forEach(admin -> listAdminDTO.add(adminMapper.adminToAdminDTO(admin)));

		return listAdminDTO;
	}

	@Override
	public AdminDTO updateAdmin(@NotNull @Valid UpdateAdminRequest adminDTO) {
		Long idUser = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		
		Admin admin = this.findOne(idUser);
		this.adminMapper.updateAdminFromUpdateAdminRequest(adminDTO, admin);
		admin = adminRepository.save(admin);

		return adminMapper.adminToAdminDTO(admin);
	}

	@Override
	public AdminDTO changePassword(@NotNull @Valid LoginRequest loginRequest) {
		Long idUser = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		Admin admin = this.findOne(idUser);
		
		admin.getUser().setPassword(passwordEncoder.encode(loginRequest.getPassword()));
		admin = adminRepository.save(admin);	
		
		mailService.sendEmailConfirmationChangePassword(admin.getUser());
		return adminMapper.adminToAdminDTO(admin);
	}

	@Override
	public AdminDTO sendMessage(@NotNull @Valid MessageRequest messageRequest) {
		Long idUser = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
		Admin admin = this.findOne(idUser);
		
		if(messageRequest.getRole() == null) {
			// Envoi d'un message à tous les étudiants et toutes les entreprises
			List<Student> listStudents = this.studentRepository.findAll();
			for(Student student: listStudents) {
				this.mailService.sendAdminMessage(messageRequest.getMessage(), student.getUser());
			}
			List<Company> listCompanies = this.companyRepository.findAll();
			for(Company company: listCompanies) {
				for(User employee: company.getEmployees()) {
					this.mailService.sendAdminMessage(messageRequest.getMessage(), employee);
				}
			}
		} else if(messageRequest.getRole().equals("COMPANY")) {
			// Envoi d'un message aux entreprises
			if(messageRequest.getState() != null) {
				List<Company> listCompanies = this.companyRepository.findAll();
				for(Company company: listCompanies) {
					if(messageRequest.getState().equals(company.getState().toString())) {
						for(User employee: company.getEmployees()) {
							this.mailService.sendAdminMessage(messageRequest.getMessage(), employee);
						}
					}
				}
			} else {
				List<Company> listCompanies = this.companyRepository.findAll();
				for(Company company: listCompanies) {
					for(User employee: company.getEmployees()) {
						this.mailService.sendAdminMessage(messageRequest.getMessage(), employee);
					}
				}
			}
		} else if(messageRequest.getRole().equals("STUDENT")) {
			// Envoi d'un message aux étudiants
			if(messageRequest.getState() != null) {
				List<Student> listStudents = this.studentRepository.findAll();
				for(Student student: listStudents) {
					if(messageRequest.getState().equals(student.getState().toString())) {
						this.mailService.sendAdminMessage(messageRequest.getMessage(), student.getUser());
					}
				}
			} else {
				List<Student> listStudents = this.studentRepository.findAll();
				for(Student student: listStudents) {
					this.mailService.sendAdminMessage(messageRequest.getMessage(), student.getUser());
				}
			}
		}
		
		return adminMapper.adminToAdminDTO(admin);
	}

}
