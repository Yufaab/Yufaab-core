package com.yufaab.yufaabcore.rest.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
  private boolean newOrder;
  private String rank;
  private String seatType;
  private String gender;
  private String categoryRank;
  private String state;
  private List<String> academicProgramName;
  private List<String> institute;
  private String examType;
  private String orderId;
}
