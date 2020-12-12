package com.polytech.recrutesup.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.polytech.recrutesup.dto.CompanyDTO;
import com.polytech.recrutesup.dto.EmployeeDTO;
import com.polytech.recrutesup.dto.EmployeeLightDTO;
import com.polytech.recrutesup.entities.Company;
import com.polytech.recrutesup.entities.User;
import com.polytech.recrutesup.payload.request.CreateCompanyRequest;
import com.polytech.recrutesup.payload.request.CreateEmployeeRequest;

@Mapper(componentModel = "spring", uses = { WishMapper.class, OfferMapper.class })
public interface CompanyMapper {

	@Mappings({ @Mapping(target = "id", ignore = true),
		        @Mapping(target = "description", ignore = true),
			    @Mapping(target = "state", ignore = true),
			    @Mapping(target = "employees", ignore = true) })
	Company createCompanyRequestToCompany(CreateCompanyRequest createCompanyDTO);

	@Mappings({
		@Mapping(target = "employees", source = "company.employees"),
        @Mapping(target = "wishSendList", source = "company.wishSendList"),
        @Mapping(target = "offers", source = "company.offers")})
	CompanyDTO companyToCompanyDTO(Company company);

	@Mappings({ @Mapping(target = "id", ignore = true) })
	void updateCompanyFromCreateCompanyRequest(CreateCompanyRequest companyDTO, @MappingTarget Company company);

	@Mappings({ @Mapping(target = "id", source = "employee.id"),
		        @Mapping(target = "idCompany", source = "company.id"),
			    @Mapping(target = "companyName", source = "company.name"),
			    @Mapping(target = "mailAddress", source = "employee.mailAddress")})
	EmployeeDTO userToEmployeeDTO(User employee, Company company);
	
	EmployeeLightDTO userToEmployeeLightDTO(User user);
	
	void updateEmployeeFromCreateEmployeeRequest(CreateEmployeeRequest employeeDTO, @MappingTarget User employee);
}
