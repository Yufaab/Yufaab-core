package com.yufaab.yufaabcore.dao.domain;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

@Data
@EntityScan
public class Counselling2022 {
  @Id
  private String id;
  private String institute;
  private String academicProgramName;
  private String quota;
  private String seatType;
  private String gender;
  private int openingRank;
  private int closingRank;
  private int institutePref;
  private int academicProgramNamePref;
}
