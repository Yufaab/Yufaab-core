package com.yufaab.yufaabcore.dao.repository;

import com.mongodb.client.DistinctIterable;
import com.yufaab.yufaabcore.dao.domain.Counselling2022;
import com.yufaab.yufaabcore.dao.domain.Orders;

import java.util.List;

public interface CounsellingDAL {
  List<Counselling2022> dataGenerator(Orders orders);
  List<String> getSearchQuery(String fieldName);
}
