package com.employee.managment.demo.mongo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MEmployeeDTO {
    private String id;
    private String name;
    private String department;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format")
    private String phoneNumber;

    @Email
    private String email;

    private LocationDTO location;
}
