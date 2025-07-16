package com.employee.managment.demo.mongo;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MEmployeeRepository extends MongoRepository<MEmployeeEntity, String> {

    Double METERS_PER_MILE = 1609.34;

    List<MEmployeeEntity> findByLocationNear(Point point, Distance distance);
    List<MEmployeeEntity> findByMetroStationsIn(List<String> stations);
    MEmployeeEntity findByPhoneNumber(String phoneNumber);

}
