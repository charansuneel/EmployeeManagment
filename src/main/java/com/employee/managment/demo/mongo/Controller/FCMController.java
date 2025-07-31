package com.employee.managment.demo.mongo.Controller;

import com.employee.managment.demo.ResponseUtil;
import com.employee.managment.demo.mongo.DTO.DeviceInfo;
import com.employee.managment.demo.mongo.Entity.FCMTokenEntity;
import com.employee.managment.demo.mongo.Repository.FCMTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/web-hook")
public class FCMController {

    @Autowired
    FCMTokenRepository fcmTokenRepository;

    @PostMapping("/FCM")
    public ResponseEntity<?>saveFCMToken(@RequestBody DeviceInfo data){
        try{
            FCMTokenEntity FCMData = new FCMTokenEntity(data.getEmail(), data.getFCMToken());
            fcmTokenRepository.save(FCMData);
            return ResponseUtil.genericSuccessResponseEntity("FCM Token inserted", "From user registration via mobile");
        }catch(Exception e){
            return  ResponseUtil.genericErrorResponseEntity("Error", e.getMessage());
        }
    }
}
