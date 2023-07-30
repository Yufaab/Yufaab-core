package com.yufaab.yufaabcore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AppErrorCodes {

  STUDENT_NOT_ABLE_TO_SIGNUP("Some error occurred", HttpStatus.BAD_REQUEST);
  private String message;
  private HttpStatus httpStatus;
}
