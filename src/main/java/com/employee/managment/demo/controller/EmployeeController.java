package com.employee.managment.demo.controller;

import com.employee.managment.demo.EmployeeDto;
import com.employee.managment.demo.EmployeeSummary;
import com.employee.managment.demo.entity.EmployeeEntity;
import com.employee.managment.demo.enums.Designation;
import com.employee.managment.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee")
//@AllArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseEntity<?> getEmployees(){

//        logger.info("Request Started");
        List<EmployeeEntity> employeeEntityList =  employeeService.getEmployees();

//        log.info("Request Completed");
    return ResponseEntity.ok(employeeEntityList);
    }

    @GetMapping(path = "/employee-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeDto> getEmployees(@PathVariable("id") Long id){

//        logger.info("Request Started");
        EmployeeDto employeeDto =  employeeService.getEmployees(id);

//        log.info("Request Completed");
        return ResponseEntity.ok(employeeDto);
    }


    @GetMapping(path = "/employee-summary-by-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployeeSummary> getEmployeeSummary(@PathVariable("id") Long id){

//        logger.info("Request Started");
        EmployeeSummary employeeSummary =  employeeService.getEmployeeSummary(id);

//        log.info("Request Completed");
        return ResponseEntity.ok(employeeSummary);
    }
    @PostMapping("/insert")
    public ResponseEntity<EmployeeEntity> insertEmployee(@RequestBody EmployeeEntity employeeEntity,@RequestParam Designation designation){

//        logger.info("Request Started");
        EmployeeEntity employeeEntityResult =  employeeService.insertEmployees(employeeEntity);

//        log.info("Request Completed");
        return ResponseEntity.ok(employeeEntityResult);
    }
}
