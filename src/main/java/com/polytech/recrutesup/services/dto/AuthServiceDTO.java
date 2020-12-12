package com.polytech.recrutesup.services.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.UserDTO;
import com.polytech.recrutesup.payload.request.LoginRequest;

public interface AuthServiceDTO {

	UserDTO authenticateUser(@NotNull @Valid LoginRequest loginRequest);
}
