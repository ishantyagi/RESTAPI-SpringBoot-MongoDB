package com.user.controller;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.user.constants.CommonConstants;
import com.user.repository.model.User;
import com.user.request.UserDTO;
import com.user.response.ErrorResponse;
import com.user.response.ResponseUtils;
import com.user.service.IUserService;
import com.user.service.response.UsersResponse;
import com.user.validator.UserValidator;

@RestController
@RequestMapping("/users")
public class UserNotesController {
	
	@Autowired 
	UserValidator userValidator;
	
	@Autowired
	IUserService userService;
	
	  Logger logger = LoggerFactory.getLogger(UserNotesController.class);
	
	/**
	 * REST endpoint to save user
	 */
	@ApiOperation(value = "API to save user ")
	@RequestMapping(value = "/{username}", method = RequestMethod.POST, produces = "application/json")
	public HttpEntity<Object> saveUser( @RequestBody(required = true) UserDTO request, @PathVariable(value = "username", required = true) String username ) {
		// Step1::
		logger.info("Method entered :: UserController.java . saveUser :: username"+ username);
		// Step2:: Create headers to be returned in response
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		//Validate Request
		List<String> errors = new ArrayList<String>();
		errors = userValidator.validateUser(request);
		if(errors.size() > 0) {
			return ResponseUtils.sendResponse(new ErrorResponse(CommonConstants.BAD_REQUEST, errors.toString()),headers, HttpStatus.BAD_REQUEST);
		}
		Boolean isExisting = userValidator.checkUsernameExists(username);
		if(isExisting){
			return ResponseUtils.sendResponse(new ErrorResponse(CommonConstants.BAD_REQUEST, "username already exists"),headers, HttpStatus.CONFLICT);
		}
		User response = userService.saveUsers(request, username);		
		return ResponseUtils.sendResponse(response,headers, status);
	}
	/**
	 * REST endpoint to save user notes for a given userId
	 */
	@ApiOperation(value = "API to get user notes")
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public HttpEntity<Object> getUsers(){			
		// Step1::
		logger.info("Method entered :: UserController.java . getUsers");
		// Step2:: Create headers to be returned in response
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		headers.setContentType(MediaType.APPLICATION_JSON);				
		UsersResponse response = userService.getUsers();	
		return ResponseUtils.sendResponse(response,headers, status);
	}
	
	/**
	 * REST endpoint to save user notes for a given userId
	 */
	@ApiOperation(value = "API to get user notes by username")
	@RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = "application/json")
	public HttpEntity<Object> getUser(@PathVariable(value = "username", required = true) String username ){			
		// Step1::
		logger.info("Method entered :: UserController.java . getUser for username {}", username);
		// Step2:: Create headers to be returned in response
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		headers.setContentType(MediaType.APPLICATION_JSON);
				
		User user = userService.getUser(username);
		if(user != null){
			return ResponseUtils.sendResponse(user,headers, status);
		}else{
			return ResponseUtils.sendResponse(new ErrorResponse(CommonConstants.BAD_REQUEST, "user with requested username do not exists"),headers, HttpStatus.BAD_REQUEST);
		}
		
	}
	/**
	 * REST endpoint to delete user for a given username
	 */
	@ApiOperation(value = "API to delete user notes by id")
	@RequestMapping(value = "/{username}", method = RequestMethod.DELETE, produces = "application/json")
	public HttpEntity<Object> deleteUser(@PathVariable(value = "username", required = true) String username) {
		// Step1::
		logger.info("Method entered :: UserController.java . deleteUser :: username"
				+ username);
		// Step2:: Create headers to be returned in response
		HttpHeaders headers = new HttpHeaders();
		HttpStatus status = HttpStatus.OK;
		headers.setContentType(MediaType.APPLICATION_JSON);		
		Boolean response = userService.deleteUser(username);	
		if(response == true){
			return ResponseUtils.sendResponse(response,headers, status);
		}else{
			return ResponseUtils.sendResponse(new ErrorResponse(CommonConstants.BAD_REQUEST, "unable to delete user"),headers, HttpStatus.BAD_REQUEST);
		}
	}
	
}
