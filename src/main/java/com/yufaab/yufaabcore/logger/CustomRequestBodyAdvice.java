package com.yufaab.yufaabcore.logger;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@ControllerAdvice
public class CustomRequestBodyAdvice extends RequestBodyAdviceAdapter {

  private static final Logger log = LoggerFactory.getLogger(CustomRequestBodyAdvice.class);

  @Autowired
  private LoggingService loggingService;

  @Autowired
  private HttpServletRequest httpServletRequest;

  @Override
  public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
    return true;
  }

  @Override
  public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                              Class<? extends HttpMessageConverter<?>> converterType) {
    loggingService.logRequest(httpServletRequest, body, true);
    return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
  }
}
