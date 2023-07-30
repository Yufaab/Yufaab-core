package com.yufaab.yufaabcore.dao.repository;

import com.yufaab.yufaabcore.dao.domain.Students;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Students,String> {
  Students findByEmailAndPassword(String email, String password);
}
