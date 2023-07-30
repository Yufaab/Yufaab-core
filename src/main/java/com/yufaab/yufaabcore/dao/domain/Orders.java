package com.yufaab.yufaabcore.dao.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Orders {
  @Id
  private String id;
  private String rank;
  private String seatType;
  private String gender;
  private String categoryRank;
  private String state;
  private List<String> academicProgramName;
  private List<String> institute;
  private String examType;
  private String orderedBy;
}
