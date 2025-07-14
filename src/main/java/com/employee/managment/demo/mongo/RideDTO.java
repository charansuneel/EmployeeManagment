package com.employee.managment.demo.mongo;

import lombok.Data;

import java.util.Date;

@Data
public class RideDTO {
    private String title;
    private String from;
    private String to;
    private String phoneNo;
    private String description;
    private Integer capacity;
}
