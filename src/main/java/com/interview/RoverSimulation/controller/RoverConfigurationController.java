package com.interview.RoverSimulation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.RoverSimulation.entity.ResponseMessage;
import com.interview.RoverSimulation.entity.Rover;
import com.interview.RoverSimulation.util.Configuration;

@RestController
public class RoverConfigurationController {

	@PostMapping(value = "/api/rover/configure", consumes = "application/json")
	public ResponseMessage setEnvironmentProperties(@RequestBody Rover roverEnvironmentParam) {
		Configuration.roverProperties = roverEnvironmentParam;
		Configuration.roverStatus.setBattery(Configuration.roverProperties.getInitialBattery());
		Configuration.roverStatus.setLocation(Configuration.roverProperties.getDeployPoint());
		ResponseMessage response = new ResponseMessage();
		response.setMessage("Rover Environment Configured Successfully");
		return response;
	}
}
