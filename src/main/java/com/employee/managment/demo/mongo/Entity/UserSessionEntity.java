package com.employee.managment.demo.mongo.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection="usersSessionEntity")
@Data
public class UserSessionEntity {

    private String uuid;
    private String employeeId;
    private String rideId;
    private Instant requestTime;

    public UserSessionEntity(String uuid, String employeeId, String rideId, Instant requestTime){
        this.uuid=uuid;
        this.employeeId=employeeId;
        this.rideId=rideId;
        this.requestTime=requestTime;
    }
}
