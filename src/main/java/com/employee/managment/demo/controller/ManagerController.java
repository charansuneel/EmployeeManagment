package com.employee.managment.demo.controller;

import com.employee.managment.demo.entity.ManagerEntity;
import com.employee.managment.demo.service.ManagerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/managers")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @GetMapping("fetch-managers")
    ResponseEntity<Map> fetchManagers(){
        List<ManagerEntity> data = managerService.getAllManagers();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Managers Fetched Successfully.");
        response.put("statusCode", HttpStatus.OK.value());
        response.put("data",data);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update-status")
    ResponseEntity<Map> updateStatus(@RequestParam int id, @RequestParam boolean status){
        ManagerEntity data = managerService.getManager(id);
        managerService.updateManagerStatus(status, id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Manager status updated successfully.");
        response.put("statusCode", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }

}
