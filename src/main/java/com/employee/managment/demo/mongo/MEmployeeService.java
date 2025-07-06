package com.employee.managment.demo.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MEmployeeService {

    @Autowired
    MEmployeeRepository mEmployeeRepository;

    public MEmployeeEntity saveEmployee(MEmployeeEntity employee){
        return mEmployeeRepository.save(employee);
    }

    public List<MEmployeeEntity> findNearByRiders(FetchDTO data){
        Distance distance = new Distance(data.getDistance(), Metrics.KILOMETERS);
        Point point = new Point(data.getLocation().getX(), data.location.getY());

        return mEmployeeRepository.findByLocationNear(point, distance);
    }
}
