package com.employee.managment.demo.mongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
        entity.setMetroStations(employee.getMetroStations());
        MEmployeeEntity data = mEmployeeService.saveEmployee(entity);
        Map<String, Object> response = new HashMap<>();
        response.put("statusCode", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/fetchNearByRides")
    ResponseEntity<Map>fetchEmployees(@RequestParam double distance, @RequestParam double x, @RequestParam double y){
        Map<String, Object> response = new HashMap<>();
        FetchDTO dto = new FetchDTO();
        dto.setDistance(distance);

        LocationDTO location = new LocationDTO();
        location.setX(x);
        location.setY(y);
        dto.setLocation(location);

        List<MEmployeeEntity> employeeData = mEmployeeService.findNearByRiders(dto);

        response.put("statusCode", HttpStatus.OK.value());
        response.put("data", employeeData);
        return ResponseEntity.ok(response);
    }


}
