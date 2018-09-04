package com.{projectName}.{modualName}.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CommonRepository {

  @Autowired
  private MongoTemplate mongoTemplate;

  public <T> void saveOrUpdate(T t) {
    mongoTemplate.save(t);
  }

  public <T> void batchSaveOrUpdate(List<T> t) {
    for (T t2 : t) {
      mongoTemplate.save(t2);
    }
  }
}
