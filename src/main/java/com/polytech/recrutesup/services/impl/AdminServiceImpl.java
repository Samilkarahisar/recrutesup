package com.polytech.recrutesup.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.AdminDTO;
import com.polytech.recrutesup.entities.Admin;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mail.service.MailService;
import com.polytech.recrutesup.mappers.AdminMapper;
import com.polytech.recrutesup.payload.request.LoginRequest;
import com.polytech.recrutesup.payload.request.UpdateAdminRequest;
import com.polytech.recrutesup.repositories.AdminRepository;
import com.polytech.recrutesup.services.AdminService;
import com.polytech.recrutesup.services.dto.AdminServiceDTO;

@Service
public class AdminServiceImpl implements AdminService, AdminServiceDTO {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private AdminMapper adminMapper;
	
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
	public AdminDTO updateAdmin(@NotNull Long idUser, @NotNull UpdateAdminRequest adminDTO) {
		Admin admin = this.findOne(idUser);
		this.adminMapper.updateAdminFromUpdateAdminRequest(adminDTO, admin);
		admin = adminRepository.save(admin);

		return adminMapper.adminToAdminDTO(admin);
	}

	@Override
	public AdminDTO changePassword(@NotNull Long idUser, @NotNull @Valid LoginRequest loginRequest) {
		Admin admin = this.findOne(idUser);
		
		admin.getUser().setPassword(passwordEncoder.encode(loginRequest.getPassword()));
		admin = adminRepository.save(admin);	
		
		mailService.sendEmailConfirmationChangePassword(admin.getUser());
		return adminMapper.adminToAdminDTO(admin);
	}

}
