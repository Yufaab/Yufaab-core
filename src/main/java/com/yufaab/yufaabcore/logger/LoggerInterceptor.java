package com.yufaab.yufaabcore.logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggerInterceptor implements HandlerInterceptor {
  @Autowired
  LoggingService loggingService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    if (HttpMethod.GET.name().equals(request.getMethod())) {
      loggingService.logRequest(request, null, false);
    }
    return true;
  }
}
