package com.employee.managment.demo.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MEmployeeService {

    @Autowired
    MEmployeeRepository mEmployeeRepository;

    public MEmployeeEntity saveEmployee(MEmployeeEntity employee){
        return mEmployeeRepository.save(employee);
    }
}
