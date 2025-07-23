package com.employee.managment.demo.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSessionResponse {
    boolean isWaiting;
    String sessionToken;
    String employeeId;
    String rideId;
}
