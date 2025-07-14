package com.employee.managment.demo.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/rides")
public class RidesController {

    @Autowired
    RidesService ridesService;

    @PostMapping("/create-ride")
    public ResponseEntity<Map> createRide(RideDTO data){
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            RidesEntity rideObject = new RidesEntity();
            UUID uuid = UUID.randomUUID();
            rideObject.setRideId(uuid.toString());
            rideObject.setTitle(data.getTitle());
            rideObject.setFrom(formatter.parse(data.getFrom()));
            rideObject.setTo(formatter.parse(data.getTo()));
            rideObject.setPhoneNo(data.getPhoneNo());
            rideObject.setDescription(data.getDescription());
            rideObject.setCapacity(data.getCapacity());
            RidesEntity createdRide = ridesService.createRide(rideObject);
           response.put("data", createdRide);
            response.put("statusCode", HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        }catch(Exception e){
            log.error("Error while creating ride", e);
            response.put("statusCode", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
