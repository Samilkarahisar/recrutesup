package com.polytech.recrutesup.dto;

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
public class WishDTO {

	private Long id;
	
	private String type;

	private String sender;
	
	private Long idSender;

	private String receiver;
	
	private Long idReceiver;

	private int priority;

	private String state;
}
