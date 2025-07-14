package com.employee.managment.demo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RidesRepository extends MongoRepository<RidesEntity, String> {

}
