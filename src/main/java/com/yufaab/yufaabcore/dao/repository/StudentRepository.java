package com.yufaab.yufaabcore.dao.repository;

import com.yufaab.yufaabcore.dao.domain.Students;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Students,String> {
}
