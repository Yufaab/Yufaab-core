package com.yufaab.yufaabcore.service.externalservice;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "GoogleApi", url = "${googleApi.url}")
public interface GoogleClient {
  @GetMapping("oauth2/v1/userinfo")
  GoogleRes googleLogin(@RequestParam String access_token);

  @Getter
  @Setter
  @NoArgsConstructor
  class GoogleRes{
    private String email;
    private String givenName;
    private String familyName;
  }

}
