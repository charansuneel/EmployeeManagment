package com.employee.managment.demo.mongo.Repository;

import com.employee.managment.demo.mongo.Entity.registrationsEntity;
import com.employee.managment.demo.mongo.DTO.rideIdonly;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface registrationsRepository extends MongoRepository<registrationsEntity, String> {
    List<rideIdonly> findByPhoneNo(String phoneNumber);
}
