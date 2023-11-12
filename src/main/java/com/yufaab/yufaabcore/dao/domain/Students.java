package com.yufaab.yufaabcore.dao.domain;

import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@EntityScan
public class Students {
  @Id
  private String id;
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  private String phone;
  private List<String> orders;
  private List<String> tokens;

  public void addOrder(Orders orders) {
    if(Objects.isNull(this.orders)){
      this.orders = new ArrayList<>();
      this.orders.add(orders.getId());
      return;
    }
    this.orders.add(orders.getId());
  }

  public void saveToken(String token) {
    System.out.println("idhr bhai" + token);
    if(Objects.isNull(this.tokens)){
      this.tokens = new ArrayList<>();
      this.tokens.add(token);
      return;
    }
    this.tokens.add(token);
  }

  public void removeToken(String token) {
    if(this.tokens.contains(token)) {
      this.tokens.remove(token);
    } else {
      throw new AppException(AppErrorCodes.STUDENT_ALREADY_LOGGED_OUT);
    }
  }
}
