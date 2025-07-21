package com.employee.managment.demo.mongo;


import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class RidesService {

    @Autowired
    Jedis redisClient;

    @Autowired
    RidesRepository ridesRepository;

    @Autowired
    MEmployeeRepository mEmployeeRepository;

    @Autowired
    registrationsRepository registrationsRepository;

    @Autowired
    UserSessionRepository sessionRepository;

    public RidesEntity createRide(RidesEntity data){
        return ridesRepository.insert(data);
    }

    public List<RidesEntity> fetchAllRides(){
        return ridesRepository.findAll();
    }

    public UserSessionEntity registerRide(String id, String phoneNumber){

        LocalDateTime time = LocalDateTime.now();
        ZoneId istZone = ZoneId.of("Asia/Kolkata");
        ZonedDateTime zonedIST = time.atZone(istZone);
        long score = zonedIST.toEpochSecond();
        MEmployeeEntity data = mEmployeeRepository.findByPhoneNumber(phoneNumber);
        List<rideIdonly> registeredData = registrationsRepository.findByPhoneNo(phoneNumber);
        List<String> registeredIds = new ArrayList<>();
        for (rideIdonly p : registeredData) {
            registeredIds.add(p.getRideId());
        }
        if(data != null && !registeredIds.contains(id)){
            try {
                UUID uuid = UUID.randomUUID();
                String employeeName = data.getName();
                String employeeId = data.getId();
                UserSessionEntity userSession = new UserSessionEntity(uuid.toString(), employeeName, employeeId, zonedIST.toInstant());
                redisClient.zadd("ridesSession", score, employeeId+"+"+employeeName+"+"+score);
                redisClient.setex(uuid.toString(), 60, "ACTIVE");
//                registrationsEntity regEntity = new registrationsEntity();
//                regEntity.setRideId(id);
//                regEntity.setName(employeeName);
//                regEntity.setPhoneNo(phoneNumber);
//
               return sessionRepository.insert(userSession);
            } catch (Exception e) {
                throw new OpenApiResourceNotFoundException("Failed to register ride: " + e.getMessage());
            }
        }else{
            if(registeredIds.contains(id)){
                throw new OpenApiResourceNotFoundException("User already registered for the ride" + phoneNumber);
            }else{
            throw new OpenApiResourceNotFoundException("No user found with given phone number");}
        }
    }
}
