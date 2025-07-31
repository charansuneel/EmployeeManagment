package com.employee.managment.demo.mongo.Repository;

import com.employee.managment.demo.mongo.Entity.FCMTokenEntity;
import com.employee.managment.demo.mongo.Entity.MEmployeeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FCMTokenRepository extends MongoRepository<FCMTokenEntity, String> {
}
