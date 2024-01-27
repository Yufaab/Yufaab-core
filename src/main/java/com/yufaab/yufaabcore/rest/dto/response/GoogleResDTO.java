package com.yufaab.yufaabcore.rest.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GoogleResDTO {
  private String email;
  @JsonProperty("verified_email")
  private String verifiedEmail;
  private String name;
  @JsonProperty("given_name")
  private String givenName;
  @JsonProperty("family_name")
  private String familyName;
  private String picture;
  private String locale;
}
