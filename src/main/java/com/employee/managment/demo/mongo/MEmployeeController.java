package com.employee.managment.demo.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/employee-mongo")
public class MEmployeeController {

    @Autowired
    MEmployeeService mEmployeeService;

    @PostMapping("/insertEmployee")
    ResponseEntity<Map>saveEmployee(@RequestBody MEmployeeDTO employee){

        String id = "EMP_" + java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Point locationData = new Point(employee.getLocation().getX(), employee.getLocation().getY());
        MEmployeeEntity entity = new MEmployeeEntity();
        entity.setId(id);
        entity.setName(employee.getName());
        entity.setDepartment(employee.getDepartment());
        entity.setPhoneNumber(employee.getPhoneNumber());
        entity.setEmail(employee.getEmail());
        entity.setLocation(locationData);
        MEmployeeEntity data = mEmployeeService.saveEmployee(entity);
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
