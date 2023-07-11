package com.transaction.devsu.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;


public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObject){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message",message);
        map.put("status", status);
        map.put("data", responseObject);
        return new ResponseEntity<>(map, status);
    }

}
