package com.employee.managment.demo.mongo.DTO;

import lombok.Data;

@Data
public class RideDTO {
    private String title;
    private String from;
    private String to;
    private String phoneNo;
    private String description;
    private Integer capacity;
}
