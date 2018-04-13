package com.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.user.repository.PingRepository;

@RestController
@RequestMapping("/ping")
public class HealthCheckController {

	private Logger log = LoggerFactory.getLogger(HealthCheckController.class);
	
	@Autowired
	private PingRepository pingRepo;
	
	@RequestMapping(method = RequestMethod.GET)
	public String ping() {
		log.info("ping:: user-service is running fine");
		pingRepo.findAll();
		return "Pong";
	}
}
