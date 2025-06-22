package com.employee.managment.demo.service;

import com.employee.managment.demo.EmployeeDto;
import com.employee.managment.demo.EmployeeSummary;
import com.employee.managment.demo.entity.EmployeeEntity;
import com.employee.managment.demo.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;


    public List<EmployeeEntity> getEmployees(){

    return employeeRepository.findAll();
    }

    public EmployeeDto getEmployees(Long id){

        return employeeRepository.getEmployeesdetails(id).orElseThrow();
    }

    public EmployeeSummary getEmployeeSummary(Long id){

        return employeeRepository.findByEmployeeId(id).orElseThrow();
    }
    public EmployeeEntity insertEmployees(EmployeeEntity employeeEntity){

        return employeeRepository.save(employeeEntity);
    }


}
