package com.yufaab.yufaabcore.dao.repository;

import com.yufaab.yufaabcore.dao.domain.Counselling2022;
import com.yufaab.yufaabcore.dao.domain.Orders;
import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.Document;
import java.util.List;

@Repository
@Slf4j
public class CounsellingDALImpl implements CounsellingDAL {

  @Autowired
  MongoTemplate mongoTemplate;

  @Override
  public void dataGenerator(Orders orders) {
    try{
      final Query query = new Query().with(PageRequest.of(1,1));
      System.out.println(orders.getRank());
      Criteria criteria = new Criteria().andOperator(Criteria.where("rank").is(1));
      query.addCriteria(criteria);
      Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria));
      List<Orders> ordersList = mongoTemplate.findAll(Orders.class);
      System.out.println(ordersList.toString());
    } catch (Exception e){
      log.info("Data generator failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }
}
