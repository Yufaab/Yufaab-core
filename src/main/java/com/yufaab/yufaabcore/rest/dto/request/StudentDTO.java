package com.yufaab.yufaabcore.rest.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private String phone;
  private String googleAccessToken;
}
