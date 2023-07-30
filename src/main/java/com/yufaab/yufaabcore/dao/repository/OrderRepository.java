package com.yufaab.yufaabcore.dao.repository;

import com.yufaab.yufaabcore.dao.domain.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Orders,String> {
  List<Orders> findByOrderedBy(String userId);
}
