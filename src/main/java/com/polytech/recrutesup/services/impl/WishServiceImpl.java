package com.polytech.recrutesup.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.WishDTO;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.CompanyWish;
import com.polytech.recrutesup.entities.Offer;
import com.polytech.recrutesup.entities.Student;
import com.polytech.recrutesup.entities.StudentWish;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mappers.WishMapper;
import com.polytech.recrutesup.repositories.CompanyRepository;
import com.polytech.recrutesup.repositories.OfferRepository;
import com.polytech.recrutesup.repositories.StudentRepository;
import com.polytech.recrutesup.services.WishService;
import com.polytech.recrutesup.services.dto.WishServiceDTO;

@Service
public class WishServiceImpl implements WishServiceDTO, WishService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private WishMapper wishMapper;
	
	@Override
	public List<WishDTO> getAllWishes() {
		List<WishDTO> listWishesDTO = new ArrayList<>();
		this.companyRepository.findAll().forEach(company -> listWishesDTO.addAll(this.wishMapper.listCompanyWishSendedToListWishDTO(company.getWishSendList())));
		this.studentRepository.findAll().forEach(student -> listWishesDTO.addAll(this.wishMapper.listStudentWishSendedToListWishDTO(student.getWishSendList())));
	
		return listWishesDTO;
	}

	@Override
	public WishDTO createStudentWish(@NotNull Long idUser, @NotNull Long idOffer) {
		Student student = this.studentRepository.findByIdUser(idUser);
		if(student == null) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.STUDENT_UNKNOWN);
		}
		
		Optional<Offer> optOffer = this.offerRepository.findById(idOffer);
        if (!optOffer.isPresent()) {
            throw new RecruteSupApplicationException(RecruteSupErrorType.OFFER_UNKNOWN);
        }
        Offer offer = optOffer.get();
        
        for(StudentWish wish: student.getWishSendList()) {
        	if(wish.getStudent().getId() == student.getId() && wish.getOffer().getId() == offer.getId()) {
        		throw new RecruteSupApplicationException(RecruteSupErrorType.STUDENT_WISH_ALREADY_CREATED);
        	}
        }
        
        StudentWish studentWish = new StudentWish();
        studentWish.setStudent(student);
        studentWish.setOffer(offer);
        studentWish.setState(EWorkflowState.TRANSMIS);
        studentWish.setCreationDate(new Date(System.currentTimeMillis()));
        studentWish.setPrioritySender(student.getWishSendList().size() + 1);
        studentWish.setPriorityReceiver(offer.getWishReceivedList().size() + 1);
        
        student.getWishSendList().add(studentWish);
        offer.getWishReceivedList().add(studentWish);
        
        student = this.studentRepository.save(student);
        offer = this.offerRepository.save(offer);
        
        return this.wishMapper.studentWishSendedToWishDTO(studentWish);
	}

	@Override
	public WishDTO createCompanyWish(@NotNull Long idCompany, @NotNull Long idUser) {
		Optional<Company> optCompany = this.companyRepository.findById(idCompany);
		if (!optCompany.isPresent()) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.COMPANY_UNKNOWN);
		}
		Company company = optCompany.get();
		
		Student student = this.studentRepository.findByIdUser(idUser);
		if(student == null) {
			throw new RecruteSupApplicationException(RecruteSupErrorType.STUDENT_UNKNOWN);
		}
		
		for(CompanyWish wish: company.getWishSendList()) {
        	if(wish.getCompany().getId() == company.getId() && wish.getStudent().getId() == student.getId()) {
        		throw new RecruteSupApplicationException(RecruteSupErrorType.COMPANY_WISH_ALREADY_CREATED);
        	}
        }
		
		CompanyWish companyWish = new CompanyWish();
		companyWish.setStudent(student);
		companyWish.setCompany(company);
		companyWish.setState(EWorkflowState.TRANSMIS);
		companyWish.setCreationDate(new Date(System.currentTimeMillis()));
		companyWish.setPrioritySender(company.getWishSendList().size() + 1);
		companyWish.setPriorityReceiver(student.getWishReceivedList().size() + 1);
		
		company.getWishSendList().add(companyWish);
		student.getWishReceivedList().add(companyWish);
		
		company = this.companyRepository.save(company);
		student = this.studentRepository.save(student);
		
		return this.wishMapper.companyWishSendedToWishDTO(companyWish);	
	}
}
