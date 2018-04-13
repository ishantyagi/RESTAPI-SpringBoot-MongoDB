package com.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.user.response.ErrorResponse;

@ControllerAdvice()
public class ExceptionHandlingController {

    Logger log = LoggerFactory.getLogger(ExceptionHandlingController.class);

    
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse handleUnknownException(HttpServletResponse response, HttpServletRequest request, Exception e){
        response.setStatus(500);
        log.error("error in : " + request.getRequestURL() + " , " + e.getMessage(), e);
        ErrorResponse error = new ErrorResponse("500", "Something went wrong. " +  e.getMessage());
        return error;
    }
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorResponse handleUnrecognizedPropertyException(HttpServletResponse response, HttpServletRequest request, HttpMessageNotReadableException e){
        response.setStatus(400);
        log.warn("error in : " + request.getRequestURL() + " , " + e.getRootCause().getLocalizedMessage(), e);
        ErrorResponse error = new ErrorResponse("400", "Invalid Request, " +  e.getMessage());
        return error;
    }
    
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public ErrorResponse handleTypeMismatchException(HttpServletResponse response, HttpServletRequest request, TypeMismatchException e){
        response.setStatus(400);
        log.warn("error in : " + request.getRequestURL() + " , " + e.getRootCause().getLocalizedMessage(), e);
        ErrorResponse error = new ErrorResponse("400", "Invalid Request, " +  e.getMessage());
        return error;
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse handleArgumentNotValidException(HttpServletResponse response, HttpServletRequest request, MethodArgumentNotValidException e){
        response.setStatus(400);
        log.warn("error in : " + request.getRequestURL() + " , " + e.getMessage(), e);
        ErrorResponse error = new ErrorResponse("400", "Invalid Request, " +  e.getMessage());
        return error;
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ErrorResponse handleMissingServletRequestParameterExceptionException(HttpServletResponse response, HttpServletRequest request, MissingServletRequestParameterException e){
        response.setStatus(400);
        log.warn("error in : " + request.getRequestURL() + " , " + e.getMessage(), e);
        ErrorResponse error = new ErrorResponse("400", "Invalid Request, " +  e.getMessage());
        return error;
    }
    
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ErrorResponse handleBindException(HttpServletResponse response, HttpServletRequest request, BindException e){
        response.setStatus(400);
        log.warn("error in : " + request.getRequestURL() + " , " + e.getMessage(), e);
        ErrorResponse error = new ErrorResponse("400", "Invalid Request, " +  e.getMessage());
        return error;
    }
    
}
