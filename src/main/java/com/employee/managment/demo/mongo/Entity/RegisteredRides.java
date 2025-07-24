package com.employee.managment.demo.mongo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "registeredRides")
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredRides {
    String sessionId;
    String rideId;
    String userId;
}
