package com.employee.managment.demo.mongo.Repository;

import com.employee.managment.demo.mongo.Entity.UserSessionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSessionRepository extends MongoRepository<UserSessionEntity, String> {

}
