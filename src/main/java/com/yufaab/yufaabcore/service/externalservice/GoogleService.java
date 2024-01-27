package com.yufaab.yufaabcore.service.externalservice;

import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import com.yufaab.yufaabcore.rest.dto.response.GoogleResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
public class GoogleService {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${googleApi.url}")
  private String googleApiUri;

  public GoogleResDTO googleLogin(String accessToken) {
    try {
      HttpHeaders headers = new HttpHeaders();
      String uri = googleApiUri + "/oauth2/v1/userinfo";
      UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri)
          .queryParam("access_token", accessToken);
      HttpEntity<?> request = new HttpEntity<>(headers);
      ResponseEntity<GoogleResDTO> response = restTemplate
          .exchange(builder.toUriString(), HttpMethod.GET, request, GoogleResDTO.class);
      return response.getBody();
    } catch (Exception e) {
      log.error("Google login failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_FOUND);
    }
  }
}
