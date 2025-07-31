package com.employee.managment.demo.mongo.Entity;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "FCMTokens")
@AllArgsConstructor
public class FCMTokenEntity {

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
    private String phoneNumber;
    private String fcmToken;
}
