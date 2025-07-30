package com.employee.managment.demo.mongo.Repository;

import com.employee.managment.demo.mongo.Entity.RidesEntity;
import com.employee.managment.demo.mongo.DTO.rideIdonly;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RidesRepository extends MongoRepository<RidesEntity, String> {

    List<rideIdonly> findByPhoneNo(String phoneNumber);
    List<RidesEntity> findDataByPhoneNo(String phoneNumber);
    List<RidesEntity> findByRideId(String rideId);
}
