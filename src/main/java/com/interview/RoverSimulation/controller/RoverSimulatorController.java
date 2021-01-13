package com.interview.RoverSimulation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.RoverSimulation.entity.Direction;
import com.interview.RoverSimulation.entity.Environment;
import com.interview.RoverSimulation.entity.ResponseMessage;
import com.interview.RoverSimulation.entity.RoverEnvironment;
import com.interview.RoverSimulation.entity.RoverStatus;
import com.interview.RoverSimulation.enums.TerrainType;
import com.interview.RoverSimulation.service.RoverSimulatorService;
import com.interview.RoverSimulation.util.Configuration;

@RestController
public class RoverSimulatorController {

	@Autowired
	RoverSimulatorService roverSimulatorService;

	@PostMapping(value = "/api/rover/move", consumes = "application/json")
	public ResponseMessage moveRover(@RequestBody Direction moveDirection) {
		int currentRow = Configuration.roverProperties.getDeployPoint().getRow();
		int currentColumn = Configuration.roverProperties.getDeployPoint().getColumn();
		int battery = Configuration.roverProperties.getInitialBattery();
		Environment environmentMap = Configuration.environmentProperties;
		int maxRow = Configuration.environmentProperties.getAreaMap().length;
		int maxColumn = environmentMap.getAreaMap()[0].length;
		boolean storm = environmentMap.isStorm();
		TerrainType terrain = environmentMap.getAreaMap()[currentRow][currentColumn];
		Configuration.roverStatus.setRoverEnvironment(new RoverEnvironment(environmentMap.getTemperature(),
				environmentMap.getHumidity(), environmentMap.isSolarFlare(), environmentMap.isStorm(), terrain));
		String message = "Rover Moved Successfully";
		ResponseMessage response = new ResponseMessage();
		if (storm) {
			message = "Cannot move during storm";
		} else {
			switch (moveDirection.getDirection()) {
			case up:
				if (currentRow - 1 < 0) {
					message = "Can move only within mapped area";
				} else {
					int updatedRow = currentRow - 1;
					message = roverSimulatorService.checkRoverScenario(currentRow, currentColumn, battery, "row",
							updatedRow);
				}
				break;
			case down:
				if (currentRow + 1 >= maxRow) {
					message = "Can move only within mapped area";
				} else {
					int updatedRow = currentRow + 1;
					message = roverSimulatorService.checkRoverScenario(currentRow, currentColumn, battery, "row",
							updatedRow);
				}
				break;
			case left:
				if (currentColumn - 1 < 0) {
					message = "Can move only within mapped area";
				} else {
					int updatedColumn = currentColumn - 1;
					message = roverSimulatorService.checkRoverScenario(currentRow, currentColumn, battery, "column",
							updatedColumn);
				}
				break;
			case right:
				if (currentColumn + 1 >= maxColumn) {
					message = "Can move only within mapped area";
				} else {
					int updatedColumn = currentColumn + 1;
					message = roverSimulatorService.checkRoverScenario(currentRow, currentColumn, battery, "column",
							updatedColumn);
				}
				break;
			}
		}
		response.setMessage(message);
		return response;
	}

	@GetMapping(value = "/api/rover/status", consumes = "application/json", produces = "application/json")
	public RoverStatus getRoverStatus() {
		Environment environmentMap = Configuration.environmentProperties;
		int currentRow = Configuration.roverProperties.getDeployPoint().getRow();
		int currentColumn = Configuration.roverProperties.getDeployPoint().getColumn();
		int battery = Configuration.roverProperties.getInitialBattery();
		TerrainType terrain = environmentMap.getAreaMap()[currentRow][currentColumn];
		Configuration.roverStatus.setRoverEnvironment(new RoverEnvironment(environmentMap.getTemperature(),
				environmentMap.getHumidity(), environmentMap.isSolarFlare(), environmentMap.isStorm(), terrain));
		Configuration.roverStatus.setBattery(battery);
		Configuration.roverStatus.setInventoryItem(Configuration.roverProperties.getInventory());
		if (Configuration.roverStatus.getRoverEnvironment().isSolarFlare()) {
			Configuration.roverStatus.setBattery(11);
			Configuration.roverProperties.setInitialBattery(11);
			Configuration.roverDead = false;
		}
		if (Configuration.roverStatus.getBattery() <= 0) {
			Configuration.roverDead = true;
		}
		if (Configuration.roverDead) {
			return null;
		}
		return Configuration.roverStatus;
	}

}
