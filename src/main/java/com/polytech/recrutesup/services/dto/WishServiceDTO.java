package com.polytech.recrutesup.services.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.polytech.recrutesup.dto.WishDTO;
import com.polytech.recrutesup.payload.request.CreateMeetingRequest;
import com.polytech.recrutesup.payload.request.WishMessageRequest;

public interface WishServiceDTO {

	List<WishDTO> getAllWishes();
	
	WishDTO getStudentWish(@NotNull Long idWish);
	
	WishDTO getCompanyWish(@NotNull Long idWish);
	
	WishDTO createStudentWish(@NotNull Long idOffer);
	
	WishDTO createCompanyWish(@NotNull Long idUser);

	WishDTO createMeeting(@Valid @NotNull CreateMeetingRequest createMeetingRequest);
	
	WishDTO sendMessage(@Valid @NotNull WishMessageRequest messageRequest);

	WishDTO updateStateCompanyWish(@NotNull Long idWish, @NotNull String currentState, @NotNull String nextState);

	WishDTO updateStateStudentWish(@NotNull Long idWish, @NotNull String currentState, @NotNull String nextState);
}
