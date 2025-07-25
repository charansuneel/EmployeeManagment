package com.employee.managment.demo;

import com.employee.managment.demo.mongo.Entity.RegisteredRides;
import com.employee.managment.demo.mongo.Repository.RegisteredRidesRepository;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPubSub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class RedisPubSub extends JedisPubSub {

    @Autowired
    RegisteredRidesRepository registeredRidesRepository;

    @Override
    public void onPMessage(String pattern, String channel, String message) {

        if("__keyevent@0__:expired".equals(channel)){
            Claims claims = TokenGenerator.decodeToken(message);
            Map<String, Object> claimsMap = new HashMap<>(claims);
            List<RegisteredRides> rides = registeredRidesRepository.findBySessionIdAndRideId(message, (String) claimsMap.get("ride"));
            for(RegisteredRides  x : rides){
                System.out.println(x);
            }
            log.info("Found rides with the session Token");
            System.out.println("Pattern: " + pattern + ", Channel: " + channel + ", Message: " + message + "Expire Event Triggered");
        }
        System.out.println("Pattern: " + pattern + ", Channel: " + channel + ", Message: " + message);
    }
}
