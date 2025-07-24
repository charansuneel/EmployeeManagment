package com.employee.managment.demo.mongo.Controller;

import com.employee.managment.demo.mongo.Entity.MetroEntity;
import com.employee.managment.demo.mongo.Service.MetroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/metro-data")
public class MetroController {

    @Autowired
    MetroService metroService;

    @GetMapping("/fetch-metros")
    ResponseEntity<Map> fetchMetro(){
        Map<String, Object> response = new HashMap<>();
        List<MetroEntity> data = metroService.getAllMetros();

        response.put("count", data.size());
        response.put("data",data);
        response.put("statusCode", HttpStatus.OK.value());
        return ResponseEntity.ok(response);
    }
}
