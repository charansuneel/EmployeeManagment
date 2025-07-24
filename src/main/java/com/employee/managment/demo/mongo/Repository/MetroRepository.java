package com.employee.managment.demo.mongo.Repository;

import com.employee.managment.demo.mongo.Entity.MetroEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetroRepository extends MongoRepository<MetroEntity, String> {

}
