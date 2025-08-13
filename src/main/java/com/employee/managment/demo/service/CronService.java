package com.employee.managment.demo.service;

import com.employee.managment.demo.entity.EmployeeEntity;
import com.employee.managment.demo.mongo.Entity.MEmployeeEntity;
import com.employee.managment.demo.mongo.Entity.RegisteredRides;
import com.employee.managment.demo.mongo.Entity.RidesEntity;
import com.employee.managment.demo.mongo.Repository.MEmployeeRepository;
import com.employee.managment.demo.mongo.Repository.RegisteredRidesRepository;
import com.employee.managment.demo.mongo.Repository.RidesRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CronService {

    @Autowired
    RegisteredRidesRepository registeredRidesRepository;
    @Autowired
    MEmployeeRepository mEmployeeRepository;
    @Autowired
    RidesRepository ridesRepository;

    public String testScheduler() throws Exception{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("registrationData");
        Integer row = 0;
        Row header = sheet.createRow(row);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("RideName");

        headerCell = header.createCell(1);
        headerCell.setCellValue("Name");

        headerCell = header.createCell(2);
        headerCell.setCellValue("Emailid");

        headerCell = header.createCell(3);
        headerCell.setCellValue("Phone");

        List<RegisteredRides> data = registeredRidesRepository.findAll();
        for(RegisteredRides x : data){
            Optional<MEmployeeEntity> employee = mEmployeeRepository.findById(x.getUserId());
            List<RidesEntity> rideData = ridesRepository.findByRideId(x.getRideId());
            row+=1;
            Row dataRow = sheet.createRow(row); // creates one row at a time
            dataRow.createCell(0).setCellValue(rideData.get(0).getTitle());
            dataRow.createCell(1).setCellValue(employee.get().getName());
            dataRow.createCell(2).setCellValue(employee.get().getEmail());
            dataRow.createCell(3).setCellValue(employee.get().getPhoneNumber());
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();
        byte[] excelBytes = bos.toByteArray();
        bos.close();
        log.info(Base64.getEncoder().encodeToString(excelBytes));
        return Base64.getEncoder().encodeToString(excelBytes);
    }
}
