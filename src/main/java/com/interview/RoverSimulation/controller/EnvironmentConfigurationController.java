package com.interview.RoverSimulation.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.interview.RoverSimulation.entity.Environment;
import com.interview.RoverSimulation.entity.InventoryItem;
import com.interview.RoverSimulation.entity.ResponseMessage;
import com.interview.RoverSimulation.enums.InventoryItemType;
import com.interview.RoverSimulation.enums.TerrainType;
import com.interview.RoverSimulation.util.Configuration;

@RestController
public class EnvironmentConfigurationController {

	@PostMapping(value = "/api/environment/configure", consumes = "application/json")
	public ResponseMessage setEnvironmentProperties(@RequestBody Environment environmentParam) {
		Configuration.environmentProperties = environmentParam;
		ResponseMessage response = new ResponseMessage();
		response.setMessage("Initial Environment Configured Successfully");
		return response;
	}

	@PatchMapping(value = "api/environment", consumes = "application/json", produces = "application/json")
	public Environment updateEnvironmentProperties(@RequestBody Map<String, Object> environmentMap) {
		try {
			environmentMap.entrySet().stream().forEach(property -> {
				switch (property.getKey()) {
				case "temperature":
					Configuration.environmentProperties.setTemperature((int) property.getValue());
					break;
				case "humidity":
					Configuration.environmentProperties.setHumidity((int) property.getValue());
					break;
				case "solarFlare":
					Configuration.environmentProperties.setSolarFlare((boolean) property.getValue());
					break;
				case "storm":
					Configuration.environmentProperties.setStorm((boolean) property.getValue());
					if ((boolean) property.getValue()) {
						InventoryItem[] inventoryItem = Configuration.roverProperties.getInventory();
						List<InventoryItem> newList = new ArrayList<>();
						boolean stormShield = false;
						for (InventoryItem item : inventoryItem) {
							if (item.getType().equals(InventoryItemType.StormShield)) {
								int quantity = item.getQuantity() - 1;
								stormShield = true;
								if (quantity > 0) {
									newList.add(new InventoryItem(item.getType(), quantity, 1));
								}
							} else {
								newList.add(item);
							}
						}
						if (!stormShield) {
							Configuration.roverDead = true;
						}
						if (!newList.isEmpty()) {
							Configuration.roverProperties.setInventory((InventoryItem[]) newList.toArray());
						} else {
							Configuration.roverProperties.setInventory(new InventoryItem[0]);
						}
					}
					break;
				case "areaMap":
					Configuration.environmentProperties.setAreaMap((TerrainType[][]) property.getValue());
					break;
				}
			});
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Configuration.environmentProperties;
	}

}
