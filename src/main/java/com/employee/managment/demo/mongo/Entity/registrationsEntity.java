package com.employee.managment.demo.mongo.Entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "registrationEntity")
public class registrationsEntity {
    private String rideId;
    private String phoneNo;
    private String name;
}
