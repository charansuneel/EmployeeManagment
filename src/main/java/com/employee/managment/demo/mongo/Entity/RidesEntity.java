package com.employee.managment.demo.mongo.Entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Data
@Document(collection = "RidesData")
public class RidesEntity {
    private String rideId = UUID.randomUUID().toString();
    private String title;
    private Date from;
    private Date to;
    private String phoneNo;
    private String description;
    private Integer capacity;
}
