package com.polytech.recrutesup.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.polytech.recrutesup.dto.CreateStudentDTO;
import com.polytech.recrutesup.dto.StudentDTO;
import com.polytech.recrutesup.entities.Student;
import com.polytech.recrutesup.entities.User;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "label", ignore = true),
		@Mapping(target = "description", ignore = true),
		@Mapping(target = "state", ignore = true)
	})
	Student createStudentDTOToStudent(CreateStudentDTO createStudentDTO, User user);
	
	
	@Mappings({
		@Mapping(source = "user.firstname", target = "firstname"),
		@Mapping(source = "user.lastname", target = "lastname"),
		@Mapping(source = "user.mailAddress", target = "mailAddress"),
		@Mapping(source = "user.phoneNumber", target = "phoneNumber")
	})
	StudentDTO studentToStudentDTO(Student student);
}
