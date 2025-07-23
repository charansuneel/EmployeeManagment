package com.employee.managment.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
@Data
@AllArgsConstructor
public class EmployeeDto {
    private String firstName;
    private LocalDate dateOfBirth;
}
