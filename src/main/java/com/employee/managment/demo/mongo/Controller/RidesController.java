package com.employee.managment.demo.mongo.Controller;

import com.employee.managment.demo.ResponseUtil;
import com.employee.managment.demo.mongo.DTO.RideDTO;
import com.employee.managment.demo.mongo.Entity.RegisteredRides;
import com.employee.managment.demo.mongo.Entity.RidesEntity;
import com.employee.managment.demo.mongo.Service.RidesService;
import com.employee.managment.demo.mongo.UserSessionResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Slf4j
@RequestMapping("/rides")
public class RidesController {

    @Autowired
    RidesService ridesService;

    @Autowired
    Jedis redisClient;

    @PostMapping("/create-ride")
    public ResponseEntity<Map> createRide(RideDTO data){
        Map<String, Object> response = new HashMap<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try{
            RidesEntity rideObject = new RidesEntity();
            UUID uuid = UUID.randomUUID();
            rideObject.setRideId(uuid.toString());
            rideObject.setTitle(data.getTitle());
            rideObject.setFrom(formatter.parse(data.getFrom()));
            rideObject.setTo(formatter.parse(data.getTo()));
            rideObject.setPhoneNo(data.getPhoneNo());
            rideObject.setDescription(data.getDescription());
            rideObject.setCapacity(data.getCapacity());
            RidesEntity createdRide = ridesService.createRide(rideObject);
            response.put("data", createdRide);
            response.put("statusCode", HttpStatus.OK.value());
            redisClient.setnx(uuid.toString(),"0");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            log.error("Error while creating ride", e);
            response.put("statusCode", HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping("/get-rides")
    ResponseEntity<Object> getRides(){
        try {
            List<RidesEntity> data = ridesService.fetchAllRides();
            return ResponseUtil.genericSuccessResponseEntity(data, "Rides Fetched Successfully");
        }catch(Exception e){
            return ResponseUtil.genericErrorResponseEntity(Collections.EMPTY_LIST, "Error while Fetching Ride Details");
        }
    }

    @PostMapping("/register-ride")
    public ResponseEntity<?> registerRide(@RequestParam String rideId, @RequestParam String phoneNumber) {
        try {
            UserSessionResponse sessionData = ridesService.createSession(rideId, phoneNumber);

            // Create Excel in memory
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Trip Expenses");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Name");
            header.createCell(1).setCellValue("Amount");
            header.createCell(2).setCellValue("Date");
            header.createCell(3).setCellValue("Place");

            workbook.write(out);
            workbook.close();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDisposition(ContentDisposition.attachment().filename("rides.xlsx").build());

            return new ResponseEntity<>(sessionData, HttpStatus.OK);

        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseUtil.genericErrorResponseEntity("Registration Failed", "Unexpected error: " + ex.getMessage());
        }
    }

    @PostMapping("/submit-ride")
    ResponseEntity<?>submitRegistration(@RequestBody String token){
        try{
            RegisteredRides data = ridesService.submit(token);
            return new ResponseEntity<>(data, HttpStatus.OK);
        }catch(Exception ex){
            return ResponseUtil.genericErrorResponseEntity("Verification Failed", "Unexpected error: " + ex.getMessage());
        }
    }

    @GetMapping("/fetch-data")
    ResponseEntity<?>getMasterData(@RequestParam String phoneNumber){
        return ridesService.fetchMasterData(phoneNumber);
    }

}
