package com.yufaab.yufaabcore.rest.dto.request;

import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
  private String rank;
  private String seatType;
  private String gender;
  private String categoryRank;
  private String state;
  private List<String> academicProgramName;
  private List<String> institute;
  private String examType;
  private String orderId;

  public void isValid() {
    if(StringUtils.isEmpty(orderId)){
      throw new AppException(AppErrorCodes.ORDER_NOT_FOUND);
    }
  }
}
