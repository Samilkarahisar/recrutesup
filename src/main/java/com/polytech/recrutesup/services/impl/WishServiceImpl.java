package com.polytech.recrutesup.services.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polytech.recrutesup.dto.WishDTO;
import com.polytech.recrutesup.entities.Admin;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.CompanyWish;
import com.polytech.recrutesup.entities.Offer;
import com.polytech.recrutesup.entities.Student;
import com.polytech.recrutesup.entities.StudentWish;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.entities.reference.EWorkflowState;
import com.polytech.recrutesup.exceptions.RecruteSupApplicationException;
import com.polytech.recrutesup.exceptions.RecruteSupErrorType;
import com.polytech.recrutesup.mail.service.MailService;
import com.polytech.recrutesup.mappers.WishMapper;
import com.polytech.recrutesup.payload.request.CreateMeetingRequest;
import com.polytech.recrutesup.repositories.AdminRepository;
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
	private AdminRepository adminRepository;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private WishMapper wishMapper;
	
	@Override
	public WishDTO getStudentWish(Long id) {
		List<Student> listStudents = this.studentRepository.findAll();
		for(Student student: listStudents) {
			for(StudentWish wish: student.getWishSendList()) {
				if(wish.getId() == id) {
					return this.wishMapper.studentWishSendedToWishDTO(wish);
				}
			}
		}
		
		throw new RecruteSupApplicationException(RecruteSupErrorType.WISH_UNKNOWN);
	}

	@Override
	public WishDTO getCompanyWish(Long id) {
		List<Company> listCompanies = this.companyRepository.findAll();
		for(Company company: listCompanies) {
			for(CompanyWish wish: company.getWishSendList()) {
				if(wish.getId() == id) {
					return this.wishMapper.companyWishSendedToWishDTO(wish);
				}
			}
		}
		
		throw new RecruteSupApplicationException(RecruteSupErrorType.WISH_UNKNOWN);
	}
	
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
        
        this.mailService.sendEmailConfirmationStudentWish(student, offer);
        
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
		
		this.mailService.sendEmailCreationCompanyWish(company, student);
		
		return this.wishMapper.companyWishSendedToWishDTO(companyWish);	
	}

	@Override
	public WishDTO createMeeting(@Valid @NotNull CreateMeetingRequest createMeetingRequest) {
		if(createMeetingRequest.getType().equals("COMPANY")) {
			List<Company> listCompanies = this.companyRepository.findAll();
			for(Company company: listCompanies) {
				for(CompanyWish wish: company.getWishSendList()) {
					if(wish.getId() == createMeetingRequest.getIdWish() && wish.getState().equals(EWorkflowState.VALIDE)) {
						Optional<User> optEmployee = company.getEmployees().stream().filter(e -> e.getId() == createMeetingRequest.getIdSender()).findFirst();
						User employee = null;
						if(optEmployee != null) {
							employee = optEmployee.get();
						} else {
							throw new RecruteSupApplicationException(RecruteSupErrorType.EMPLOYEE_UNKNOWN);
						}
						
						Student student = this.studentRepository.findByIdUser(createMeetingRequest.getIdInterlocutor());
						if(student == null) {
							throw new RecruteSupApplicationException(RecruteSupErrorType.STUDENT_UNKNOWN);
						}
						
						if(createMeetingRequest.getIdReceiver() != null) {
							this.mailService.sendEmailMeetingCreationRequest(createMeetingRequest.getDateMeeting(),
																			 createMeetingRequest.getMessage(),
																			 employee,
																			 company,
																			 student,
																			 student.getUser());
						} else {
							List<Admin> admins = this.adminRepository.findAll();
							this.mailService.sendEmailMeetingCreationRequestToAdmin(createMeetingRequest.getDateMeeting(),
																				    createMeetingRequest.getMessage(),
																				    employee,
																				    company,
																				    student,
																					admins);
						}
						
						return this.wishMapper.companyWishSendedToWishDTO(wish);
					}
				}
			}
			
			throw new RecruteSupApplicationException(RecruteSupErrorType.WISH_INCORRECT);
			
		} else if(createMeetingRequest.getType().equals("STUDENT")) {
			Student student = this.studentRepository.findByIdUser(createMeetingRequest.getIdSender());
			if(student == null) {
				throw new RecruteSupApplicationException(RecruteSupErrorType.STUDENT_UNKNOWN);
			}
			for(StudentWish wish: student.getWishSendList()) {
				if(wish.getId() == createMeetingRequest.getIdWish() && wish.getState().equals(EWorkflowState.VALIDE)) {
					Optional<Offer> optOffer = offerRepository.findById(createMeetingRequest.getIdInterlocutor());
					Offer offer = null;
					if(optOffer != null) {
						offer = optOffer.get();
					} else {
						throw new RecruteSupApplicationException(RecruteSupErrorType.OFFER_UNKNOWN);
					}
					
					if(createMeetingRequest.getIdReceiver() != null) {
						this.mailService.sendEmailMeetingCreationRequest(createMeetingRequest.getDateMeeting(),
								 										 createMeetingRequest.getMessage(),
																		 student,
																		 offer,
																		 offer.getCreatedByUser());
					} else {
						List<Admin> admins = this.adminRepository.findAll();
						this.mailService.sendEmailMeetingCreationRequestToAdmin(createMeetingRequest.getDateMeeting(),
																				createMeetingRequest.getMessage(),
																				student,
																				offer,
																				admins);
					}
					
					return this.wishMapper.studentWishSendedToWishDTO(wish);
				}
			}
			
			throw new RecruteSupApplicationException(RecruteSupErrorType.WISH_INCORRECT);
		}
		
		throw new RecruteSupApplicationException(RecruteSupErrorType.WISH_BAD_TYPE);
	}
}
