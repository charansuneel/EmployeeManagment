package com.employee.managment.demo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface registrationsRepository extends MongoRepository<registrationsEntity, String> {
}
