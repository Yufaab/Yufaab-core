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

  @Mapping(source = "rank", target = "rank")
  @Mapping(source = "seatType", target = "seatType")
  @Mapping(source = "gender", target = "gender")
  @Mapping(source = "categoryRank", target = "categoryRank")
  @Mapping(source = "state", target = "state")
  @Mapping(source = "academicProgramName", target = "academicProgramName")
  @Mapping(source = "institute", target = "institute")
  @Mapping(source = "examType", target = "examType")
  @Mapping(source = "orderedBy", target = "orderedBy")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Orders orderDTOtoOrder(OrderDTO orderDTO);
}
