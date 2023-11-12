package com.yufaab.yufaabcore.rest.dto.mapper;

import com.yufaab.yufaabcore.dao.domain.Orders;
import com.yufaab.yufaabcore.rest.dto.request.OrderDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {
  static OrderMapper getMapper() {
    return Mappers.getMapper(OrderMapper.class);
  }

  @Mapping(source = "orderDTO.rank", target = "rank")
  @Mapping(source = "orderDTO.seatType", target = "seatType")
  @Mapping(source = "orderDTO.gender", target = "gender")
  @Mapping(source = "orderDTO.categoryRank", target = "categoryRank")
  @Mapping(source = "orderDTO.state", target = "state")
  @Mapping(source = "orderDTO.academicProgramName", target = "academicProgramName")
  @Mapping(source = "orderDTO.institute", target = "institute")
  @Mapping(source = "orderDTO.examType", target = "examType")
  @Mapping(source = "userId", target = "orderedBy")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Orders orderDTOtoOrder(OrderDTO orderDTO, String userId);
}
