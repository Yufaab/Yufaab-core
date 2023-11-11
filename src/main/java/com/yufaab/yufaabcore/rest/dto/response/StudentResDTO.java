package com.yufaab.yufaabcore.rest.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResDTO {
  private String id;
  private String firstname;
  private String lastname;
  private String email;
  private String phone;
  private String token;
}
