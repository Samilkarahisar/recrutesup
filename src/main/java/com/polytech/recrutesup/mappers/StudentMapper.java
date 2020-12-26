package com.polytech.recrutesup.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.entities.Student;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.payload.request.CreateStudentRequest;

@Mapper(componentModel = "spring", uses = { WishMapper.class })
public interface StudentMapper {

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "label", ignore = true),
		@Mapping(target = "description", ignore = true),
		@Mapping(target = "state", ignore = true)
	})
	Student createStudentRequestToStudent(CreateStudentRequest createStudentDTO, User user);
	
	
	@Mappings({
		@Mapping(source = "user.id", target = "id"),
		@Mapping(source = "user.firstname", target = "firstname"),
		@Mapping(source = "user.lastname", target = "lastname"),
		@Mapping(source = "user.mailAddress", target = "mailAddress"),
		@Mapping(source = "user.phoneNumber", target = "phoneNumber"),
		@Mapping(target = "wishSendList", source = "wishSendList", qualifiedByName = { "WishMapper", "listStudentWishSended" }),
		@Mapping(target = "wishReceivedList", source = "wishReceivedList", qualifiedByName = { "WishMapper", "listCompanyWishReceived" })
	})
	StudentDTO studentToStudentDTO(Student student);
	
	@Mappings({
		@Mapping(target = "user.firstname", source = "firstname"),
		@Mapping(target = "user.lastname", source = "lastname"),
		@Mapping(target = "user.mailAddress", source = "mailAddress"),
		@Mapping(target = "user.phoneNumber", source = "phoneNumber") })
	void updateStudentFromCreateStudentRequest(CreateStudentRequest studentDTO, @MappingTarget Student student);
}
