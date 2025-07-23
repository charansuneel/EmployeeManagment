package com.employee.managment.demo.mongo;


import com.employee.managment.demo.TokenGenerator;
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
//              UUID uuid = UUID.randomUUID();
                String employeeName = data.getName();
                String employeeId = data.getId();
                String token = TokenGenerator.createToken(id, employeeId);
                log.info(token,"GeneratedToken");
                UserSessionEntity userSession = new UserSessionEntity(token, employeeName, employeeId, zonedIST.toInstant());
                redisClient.zadd("ridesSession", score, employeeId+"+"+employeeName+"+"+score);
                redisClient.setex(token, 60, "ACTIVE");
                redisClient.incr("COUNTER_KEY");
                log.info(redisClient.get("COUNTER_KEY"),"THIS IS THE VALUE OF THE COUNTER KEY");
//                registrationsEntity regEntity = new registrationsEntity();
//                regEntity.setRideId(id);
//                regEntity.setName(employeeName);
//                regEntity.setPhoneNo(phoneNumber);
//                registrationsRepository.insert(regEntity);
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

    public UserSessionResponse createSession(String rideId, String phoneNumber){

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

        if(data != null && !registeredIds.contains(rideId)){
            try {
                String employeeName = data.getName();
                String employeeId = data.getId();
                String token = TokenGenerator.createToken(rideId, employeeId);
                log.info(token,"GeneratedToken");
                redisClient.zadd("ridesSession", score, employeeId+"+"+employeeName+"+"+score);
                redisClient.setex(token, 60, "ACTIVE");
                Long Counter = redisClient.incr("REGISTER_COUNT");
                log.info(redisClient.get("REGISTER_COUNT"),"THIS IS THE VALUE OF THE COUNTER KEY");

                UserSessionEntity userSession = new UserSessionEntity(token, employeeName, employeeId, zonedIST.toInstant());
                if(Counter <= 5){
                    sessionRepository.insert(userSession);
                    return new UserSessionResponse(false, token, employeeId, rideId);
                }else{
                    return new UserSessionResponse(true, "", "", "");
                }
            }catch(Exception e){
                throw new OpenApiResourceNotFoundException("Failed to register ride");
            }
        }else{
            if(registeredIds.contains(rideId)){
                throw new OpenApiResourceNotFoundException("User already registered for the ride" + phoneNumber);
            }else{
                throw new OpenApiResourceNotFoundException("No user found with given phone number");}
        }
    }
}
