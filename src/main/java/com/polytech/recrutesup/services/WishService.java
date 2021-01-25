package com.polytech.recrutesup.services;

import com.polytech.recrutesup.entities.CompanyWish;
import com.polytech.recrutesup.entities.StudentWish;

public interface WishService {
	
	CompanyWish findOneCompanyWish(Long id);
	
	StudentWish findOneStudentWish(Long id);
}
