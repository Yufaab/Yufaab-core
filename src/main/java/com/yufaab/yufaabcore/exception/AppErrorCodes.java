package com.yufaab.yufaabcore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum AppErrorCodes {

  STUDENT_NOT_ABLE_TO_SIGNUP("Signup failed due to some error", HttpStatus.BAD_REQUEST),
  STUDENT_NOT_ABLE_TO_LOGIN("Login failed due to some error", HttpStatus.BAD_REQUEST),
  STUDENT_NOT_FOUND("No student found", HttpStatus.NOT_FOUND),
  ORDER_NOT_FOUND("Order not found", HttpStatus.NOT_FOUND),
  ORDER_NOT_CREATED_OR_UPDATED("Unable to create or update order", HttpStatus.BAD_REQUEST),
  UNABLE_TO_GENERATE_REPORT("Not able to generate report", HttpStatus.BAD_REQUEST);
  private final String message;
  private final HttpStatus httpStatus;
}
