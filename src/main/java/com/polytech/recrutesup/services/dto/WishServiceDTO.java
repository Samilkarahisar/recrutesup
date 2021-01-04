package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.WishDTO;
import com.polytech.recrutesup.payload.request.CreateMeetingRequest;

public interface WishServiceDTO {

	List<WishDTO> getAllWishes();
	
	WishDTO getStudentWish(@NotNull Long idWish);
	
	WishDTO getCompanyWish(@NotNull Long idWish);
	
	WishDTO createStudentWish(@NotNull Long idUser, @NotNull Long idOffer);
	
	WishDTO createCompanyWish(@NotNull Long idCompany, @NotNull Long idUser);

	WishDTO createMeeting(@Valid @NotNull CreateMeetingRequest createMeetingRequest);
}
