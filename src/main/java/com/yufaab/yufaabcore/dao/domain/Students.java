package com.yufaab.yufaabcore.dao.domain;

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

  public void addOrder(Orders orders) {
    if(Objects.isNull(this.orders)){
      this.orders = new ArrayList<>();
      this.orders.add(orders.getId());
      return;
    }
    this.orders.add(orders.getId());
  }
}
