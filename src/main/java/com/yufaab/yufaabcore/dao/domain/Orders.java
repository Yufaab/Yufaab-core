package com.yufaab.yufaabcore.dao.domain;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@EntityScan
public class Orders {
  @Id
  private String id;
  private int rank;
  private String seatType;
  private String gender;
  private String categoryRank;
  private String state;
  private List<String> academicProgramName;
  private List<String> institute;
  private String examType;
  private String orderedBy;
}
