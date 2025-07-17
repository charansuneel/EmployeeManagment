package com.employee.managment.demo.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RidesRepository extends MongoRepository<RidesEntity, String> {

    List<rideIdonly> findByPhoneNo(String phoneNumber);
}
