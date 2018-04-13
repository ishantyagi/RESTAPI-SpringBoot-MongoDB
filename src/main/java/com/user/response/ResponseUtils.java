package com.user.response;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseUtils {
    
    public static HttpEntity<Object> sendResponse(Object response, HttpHeaders headers, HttpStatus status) {
        return new ResponseEntity<Object>(response, headers, status);
    }    
}