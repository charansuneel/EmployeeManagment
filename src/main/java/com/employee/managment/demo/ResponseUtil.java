package com.employee.managment.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseUtil {

    public static ResponseEntity<Object> genericSuccessResponseEntity(Object data, String message){
        return new ResponseEntity<>(
                Map.of(
                        "status", "success",
                        "message", message,
                        "data", data,
                        "statusCode", HttpStatus.OK.value()
                ),
                HttpStatus.OK
        );

    }

    public static ResponseEntity<Object> genericErrorResponseEntity(Object data, String message){
        return new ResponseEntity<>(
                Map.of(
                        "status", "error",
                        "message", message,
                        "data", data,
                        "statusCode", HttpStatus.NOT_FOUND.value()
                ),
                HttpStatus.NOT_FOUND
        );

    }

    public static ResponseEntity<Object> genericErrorResponseEntity(Object data, String message, Integer statusCode){
        return new ResponseEntity<>(
                Map.of(
                        "status", "error",
                        "message", message,
                        "data", data,
                        "statusCode", statusCode
                ),
                HttpStatus.valueOf(statusCode)
        );

    }
}
