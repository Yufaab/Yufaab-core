package com.yufaab.yufaabcore.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yufaab.yufaabcore.exception.AppException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Service
public class LoggingService {

  @Autowired
  private ObjectMapper objectMapper;

  public void logRequest(HttpServletRequest httpServletRequest, Object body, boolean includeRequestBody) {
    System.out.println("I am here 2");
    try {
      List<String> requestDataList = new ArrayList<>();
      requestDataList.add("LOGGING REQUEST INFO STARTS");
      requestDataList.add(httpServletRequestToString(httpServletRequest));
      if(includeRequestBody) {
        requestDataList.add("[Request Body]: " + objectMapper.writeValueAsString(body));
      }
      else requestDataList.add("[Request Body]:  REQUEST BODY IGNORED");
      requestDataList.add("LOGGING REQUEST INFO ENDS");
      log.info(String.join(" | ", requestDataList));
    } catch (JsonProcessingException exception) {
      log.error("Logging request failed with error: {}", exception.getMessage());
    }
  }

  public void logResponse(HttpServletResponse httpServletResponse, Object body, boolean includeResponseBody) {
    try {
      List<String> respDataList = new ArrayList<>();
      respDataList.add("LOGGING RESPONSE INFO STARTS, ");
      respDataList.add("[Http Status]: " + httpServletResponse.getStatus());
      if (includeResponseBody) {
        respDataList.add("[Response Body]: " + objectMapper.writeValueAsString(body));
      } else
        respDataList.add("[Response Body]: RESPONSE BODY IGNORED");
      respDataList.add("LOGGING RESPONSE ENDS");
      log.info(String.join(" | ", respDataList));
    } catch (JsonProcessingException exception) {
      log.error("Logging response failed with error: {}", exception.getMessage());
    }
  }

  private String httpServletRequestToString(HttpServletRequest request) {
    List<String> requestInfoList = new ArrayList<>();
    requestInfoList.add("Request Method = [" + request.getMethod() + "]");
    requestInfoList.add("Request URL Path = [" + request.getRequestURL() + "]");
    if(Objects.isNull(request.getHeaderNames())) {
      requestInfoList.add("Request Headers: NONE");
      return String.join(" | ", requestInfoList);
    }
    return String.join(" | ", requestInfoList);
  }

  public void logRequestInException(AppException errorResponse, WebRequest webRequest) {
    List<String> requestExceptionList = new ArrayList<>();
    requestExceptionList.add("LOGGING REQUEST INFO IN EXCEPTION STARTS");
    requestExceptionList.add("Request URI: " + webRequest.getDescription(false));
    requestExceptionList.add("Http Status: " + errorResponse.getHttpStatus());
    requestExceptionList.add("Exception Message: " + errorResponse.getMessage());
    requestExceptionList.add("LOGGING REQUEST INFO IN EXCEPTION ENDS");
    log.info(String.join(" | ", requestExceptionList));
  }
}
