package com.employee.managment.demo.mongo;


import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RidesService {

    @Autowired
    RidesRepository ridesRepository;

    @Autowired
    MEmployeeRepository mEmployeeRepository;

    @Autowired
    registrationsRepository registrationsRepository;

    public RidesEntity createRide(RidesEntity data){
        return ridesRepository.insert(data);
    }

    public List<RidesEntity> fetchAllRides(){
        return ridesRepository.findAll();
    }

    public registrationsEntity registerRide(String id, String phoneNumber){
        MEmployeeEntity data = mEmployeeRepository.findByPhoneNumber(phoneNumber);
        if(data != null){
            try {
                String employeeName = data.getName();
                registrationsEntity regEntity = new registrationsEntity();
                regEntity.setRideId(id);
                regEntity.setName(employeeName);
                regEntity.setPhoneNo(phoneNumber);

                return registrationsRepository.insert(regEntity);
            } catch (Exception e) {
                throw new OpenApiResourceNotFoundException("Failed to register ride: " + e.getMessage());
            }
        }else{
            throw new OpenApiResourceNotFoundException("No user found with phone" + phoneNumber);
        }
    }
}
