package com.yufaab.yufaabcore.dao.repository;

import com.yufaab.yufaabcore.dao.domain.Counselling2022;
import com.yufaab.yufaabcore.dao.domain.Orders;
import com.yufaab.yufaabcore.exception.AppErrorCodes;
import com.yufaab.yufaabcore.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.AddFieldsOperation.addField;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ArrayOperators.IndexOfArray.arrayOf;

@Repository
@Slf4j
public class CounsellingDALImpl implements CounsellingDAL {

  @Autowired
  MongoTemplate mongoTemplate;

  @Override
  public List<Counselling2022> dataGenerator(Orders orders) {
    try{
      AggregationResults<Counselling2022> dataPref = makePreferenceData(orders);
      AggregationResults<Counselling2022> dataCommon = makeCommonData(orders);
      return makeCombineData(dataPref, dataCommon);
    } catch (Exception e){
      log.info("Data generator failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }

  private List<Counselling2022> makeCombineData(
          AggregationResults<Counselling2022> dataPref, AggregationResults<Counselling2022> dataCommon) {
    try {
      Set<String> st = new HashSet<>();
      List<Counselling2022> filteredData = new ArrayList<>();
      for(Counselling2022 data : dataPref){
        st.add(data.getId());
        filteredData.add(data);
      }
      for(Counselling2022 data : dataCommon){
        if(!st.contains(data.getId())){
          filteredData.add(data);
        }
      }
      return filteredData;
    }catch (Exception e){
      log.info("Data generator failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }

  private AggregationResults<Counselling2022> makeCommonData(Orders orders) {
    try{
      List<AggregationOperation> aggregationList = new ArrayList<>();
      aggregationList.add(match(Criteria.where("openingRank").gte(orders.getRank())));
      aggregationList.add(match(Criteria.where("gender").is(orders.getGender())));
      aggregationList.add(match(Criteria.where("seatType").is(orders.getSeatType())));
      aggregationList.add(sort(Sort.Direction.ASC,"openingRank"));
      aggregationList.add(limit(25));
      return mongoTemplate
              .aggregate(newAggregation(aggregationList), "counselling2022", Counselling2022.class);
    }catch (Exception e){
      log.info("Data generator failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }

  private AggregationResults<Counselling2022> makePreferenceData(Orders orders) {
    try{
      List<AggregationOperation> aggregationList = new ArrayList<>();
      aggregationList.add(match(Criteria.where("openingRank").gte(orders.getRank())));
      aggregationList.add(match(Criteria.where("gender").is(orders.getGender())));
      aggregationList.add(match(Criteria.where("seatType").is(orders.getSeatType())));

      if(!Objects.isNull(orders.getInstitute())){
        aggregationList.add(match(Criteria.where("institute").in(orders.getInstitute())));
        aggregationList.add(addField("institutePref")
                .withValue(arrayOf(orders.getInstitute()).indexOf("$institute")).build());
      }

      if(!Objects.isNull(orders.getAcademicProgramName())){
        aggregationList.add(match(Criteria.where("academicProgramName").in(orders.getAcademicProgramName())));
        aggregationList.add(addField("academicProgramNamePref")
                .withValue(arrayOf(orders.getAcademicProgramName()).indexOf("$academicProgramName")).build());
      }
      aggregationList.add(sort(Sort.Direction.ASC,"institutePref","academicProgramNamePref","openingRank"));
      return mongoTemplate
              .aggregate(newAggregation(aggregationList), "counselling2022", Counselling2022.class);
    }catch (Exception e){
      log.info("Data generator failed with error: {}", e.getMessage());
      throw new AppException(AppErrorCodes.STUDENT_NOT_ABLE_TO_SIGNUP);
    }
  }
}
