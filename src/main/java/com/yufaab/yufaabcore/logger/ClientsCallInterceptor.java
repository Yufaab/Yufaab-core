package com.yufaab.yufaabcore.logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ClientsCallInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest req, byte[] reqBody, ClientHttpRequestExecution ex)
          throws IOException {
    log.debug("Logging internal restTemplate request and response");
    logRequest(req, reqBody);
    ClientHttpResponse response = ex.execute(req, reqBody);
    logResponse(response);
    return response;
  }

  private void logRequest(HttpRequest request, byte[] body) throws IOException {

    log.info("\n===========================request begin================================================ \n" +
                    "URI         : {} \n" +
                    "Method      : {} \n" +
                    "Request body: {} \n" +
                    "==========================request end================================================\n" ,
            request.getURI(), request.getMethod(), new String(body, StandardCharsets.UTF_8));
  }

  private void logResponse(ClientHttpResponse response) throws IOException {
    log.info("\n============================response begin==========================================\n" +
                    "Status code  : {} \n" +
                    "Status text  : {} \n" +
                    "Headers      : {} \n" +
                    "Response body: {} \n" +
                    "=======================response end=================================================\n",
            response.getStatusCode(), response.getStatusText(), response.getHeaders(), response);
  }
}
