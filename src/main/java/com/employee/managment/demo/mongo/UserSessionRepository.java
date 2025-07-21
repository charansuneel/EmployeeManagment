package com.employee.managment.demo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSessionRepository extends MongoRepository<UserSessionEntity, String> {

}
