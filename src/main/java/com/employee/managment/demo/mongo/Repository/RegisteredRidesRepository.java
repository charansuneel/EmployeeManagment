package com.employee.managment.demo.mongo.Repository;

import com.employee.managment.demo.mongo.Entity.MetroEntity;
import com.employee.managment.demo.mongo.Entity.RegisteredRides;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisteredRidesRepository extends MongoRepository<RegisteredRides, String> {

    public List<RegisteredRides> findBySessionIdAndRideId(String sessionId, String rideId);

}