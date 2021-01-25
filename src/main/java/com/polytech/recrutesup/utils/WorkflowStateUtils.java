package com.polytech.recrutesup.utils;

import com.polytech.recrutesup.entities.reference.ERole;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;

public class WorkflowStateUtils {

	public static boolean isNextStateAcceptable(ERole role, String cState, String nState) {
		EWorkflowState currentState, nextState;
		try {
			currentState = EWorkflowState.valueOf(cState);
			nextState = EWorkflowState.valueOf(nState);
		} catch(Exception e) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.STATE_UNKNOWN);
		}
		
		if(role.equals(ERole.ROLE_ADMIN)) {
			switch (currentState) {
				case ENREGISTRE:
					return (nextState.equals(EWorkflowState.INVALIDE) ||
							nextState.equals(EWorkflowState.VALIDE));
					
				case INVALIDE:
					return (nextState.equals(EWorkflowState.VALIDE));
					
				case VALIDE:
					return (nextState.equals(EWorkflowState.INVALIDE) ||
							nextState.equals(EWorkflowState.INDISPONIBLE));
					
				case EN_VALIDATION:
					return (nextState.equals(EWorkflowState.BROUILLON) ||
							nextState.equals(EWorkflowState.DISPONIBLE));
					
				case DISPONIBLE:
					return (nextState.equals(EWorkflowState.INDISPONIBLE));
			}
				
		} else if(role.equals(ERole.ROLE_COMPANY)) {
			switch(currentState) {
				case BROUILLON:
					return (nextState.equals(EWorkflowState.EN_VALIDATION) ||
							nextState.equals(EWorkflowState.SUPPRIME));
					
				case DISPONIBLE:
					return (nextState.equals(EWorkflowState.INDISPONIBLE));
					
				case TRANSMIS:
					return (nextState.equals(EWorkflowState.REFUSE) ||
							nextState.equals(EWorkflowState.VALIDE));
					
				case VALIDE:
					return (nextState.equals(EWorkflowState.MEETING_ORGANISE));
			}
		} else if(role.equals(ERole.ROLE_STUDENT)) {
			switch(currentState) {
				case VALIDE:
					return (nextState.equals(EWorkflowState.INDISPONIBLE) ||
							nextState.equals(EWorkflowState.MEETING_ORGANISE));
				
				case TRANSMIS:
					return (nextState.equals(EWorkflowState.REFUSE) ||
							nextState.equals(EWorkflowState.VALIDE));
			}
		}
		
		return false;
	}
}
