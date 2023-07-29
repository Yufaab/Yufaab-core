package com.yufaab.yufaabcore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AppErrorCodes {

  STUDENT_NOT_ABLE_TO_SIGNUP("Some error occurred", HttpStatus.BAD_REQUEST);
  private final String message;
  private final HttpStatus httpStatus;
}
