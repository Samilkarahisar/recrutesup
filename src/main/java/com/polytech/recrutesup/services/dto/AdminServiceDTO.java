package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.AdminDTO;
import com.polytech.recrutesup.payload.request.LoginRequest;
import com.polytech.recrutesup.payload.request.MessageRequest;
import com.polytech.recrutesup.payload.request.UpdateAdminRequest;

public interface AdminServiceDTO {

	AdminDTO getAdmin(@NotNull Long idAdmin);

	List<AdminDTO> getAllAdmins();

	AdminDTO updateAdmin(@NotNull @Valid UpdateAdminRequest adminDTO);
	
	AdminDTO changePassword(@NotNull @Valid LoginRequest loginRequest);
	
	AdminDTO sendMessage(@NotNull @Valid MessageRequest messageRequest);
}
