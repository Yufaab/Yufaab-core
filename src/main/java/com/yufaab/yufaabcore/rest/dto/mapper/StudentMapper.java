package com.yufaab.yufaabcore.rest.dto.mapper;

import com.yufaab.yufaabcore.dao.domain.Students;
import com.yufaab.yufaabcore.rest.dto.request.StudentDTO;
import com.yufaab.yufaabcore.service.externalservice.GoogleClient;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {
  static StudentMapper getMapper() {
    return Mappers.getMapper(StudentMapper.class);
  }

  @Mapping(source = "studentDTO.firstname", target = "firstname")
  @Mapping(source = "lastname", target = "lastname")
  @Mapping(source = "email", target = "email")
  @Mapping(source = "password", target = "password")
  @Mapping(source = "phone", target = "phone")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Students studentDTOtoStudents(StudentDTO studentDTO);

  @Mapping(source = "googleRes.givenName", target = "firstname")
  @Mapping(source = "googleRes.familyName", target = "lastname")
  @Mapping(source = "googleRes.email", target = "email")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Students googleResToStudents(GoogleClient.GoogleRes googleRes);
}
