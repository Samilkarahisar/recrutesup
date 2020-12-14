package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.WishDTO;

public interface WishServiceDTO {

	List<WishDTO> getAllWishes();
	
	WishDTO createStudentWish(@NotNull Long idUser, @NotNull Long idOffer);
	
	WishDTO createCompanyWish(@NotNull Long idCompany, @NotNull Long idUser);
}
