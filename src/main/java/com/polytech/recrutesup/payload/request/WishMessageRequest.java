package com.polytech.recrutesup.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WishMessageRequest {

	@NotNull
	Long idWish;
	
	@NotBlank
	String type;
	
	@NotBlank
    String message;
    
	@NotNull
    Long idSender;
    
	@NotNull
    Long idReceiver;
}
