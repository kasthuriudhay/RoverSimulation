package com.interview.RoverSimulation.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.interview.RoverSimulation.entity.InventoryItem;
import com.interview.RoverSimulation.entity.PerformCondition;
import com.interview.RoverSimulation.entity.RoverCondition;
import com.interview.RoverSimulation.entity.RoverEnvironment;
import com.interview.RoverSimulation.entity.Scenario;
import com.interview.RoverSimulation.entity.ScenarioCondition;
import com.interview.RoverSimulation.entity.State;
import com.interview.RoverSimulation.enums.Action;
import com.interview.RoverSimulation.enums.InventoryItemType;
import com.interview.RoverSimulation.enums.Operator;
import com.interview.RoverSimulation.enums.Property;
import com.interview.RoverSimulation.enums.ScenarioType;
import com.interview.RoverSimulation.util.Configuration;

@Service
public class RoverSimulatorService {

	public String checkRoverScenario(int currentRow, int currentColumn, int battery, String direction,
			int updatedValue) {
		Scenario[] scenarios = Configuration.roverProperties.getScenarios();
		boolean isImmobile = false;
		boolean collectSample = false;
		boolean itemUsage = false;
		boolean isShieldUsed = false;
		String responseMessage = null;
		InventoryItem[] itemList = new InventoryItem[3];
		int itemIndex = 0;

		List<Action> allowedActions = new ArrayList<Action>();

		for (Scenario scenario : scenarios) {
			boolean isConditionPass = false;
			for (ScenarioCondition condition : scenario.getConditions()) {
				if (condition.getType().equals(ScenarioType.rover)) {
					isConditionPass = checkCondition((Object) Configuration.roverProperties.getInitialBattery(),
							condition.getValue(), condition.getOperator());
				} else {
					isConditionPass = checkEnvironmentcondition(condition);
				}
				if (!isConditionPass) {
					break;
				}
			}
			if (isConditionPass) {
				for (RoverCondition rover : scenario.getRover()) {
					if (rover.getIs() != null) {
						String roverState = rover.getIs();
						State[] states = Configuration.roverProperties.getStates();
						for (State state : states) {
							if (state.getName().equals(roverState)) {
								allowedActions.addAll(Arrays.asList(state.getAllowedActions()));
							}
						}

					} else if (rover.getPerforms() != null) {
						PerformCondition condition = rover.getPerforms();
						if (condition.getCollectSample() != null) {
							collectSample = true;
							int priority = Configuration.samplesPriorityMap.get(condition.getCollectSample().getType());
							itemList[itemIndex++] = (new InventoryItem(condition.getCollectSample().getType(),
									condition.getCollectSample().getQuantity(), priority));

						}
						if (condition.getItemUsage() != null) {
							itemUsage = true;
						}
					}
				}
			}
		}

		if (itemUsage) {
			InventoryItem[] inventoryItemList = Configuration.roverProperties.getInventory();
			for (InventoryItem inventoryItem : inventoryItemList) {
				if (inventoryItem.getType().equals(InventoryItemType.StormShield) && inventoryItem.getQuantity() > 1) {
					isShieldUsed = true;
					int updatedQuantity = inventoryItem.getQuantity() - 1;
					if (updatedQuantity > 0) {
						itemList[itemIndex++] = (new InventoryItem(inventoryItem.getType(), updatedQuantity,
								inventoryItem.getPriority()));
					}
				}
			}
		}
		if (collectSample && itemUsage) {
			Configuration.roverProperties.setInventory(itemList);
			InventoryItem[] item = Configuration.roverProperties.getInventory();
			List<InventoryItem> list = new ArrayList<>();
			for (InventoryItem initialitem : item) {
				boolean flag = false;
				for (InventoryItem updatedItem : itemList) {
					if (initialitem.getType().equals(updatedItem.getType())) {
						flag = true;
						list.add(updatedItem);
					}
				}
				list.add(initialitem);
			}
		}
		if (!isImmobile || (itemUsage && isShieldUsed)) {
			if (direction.equals("row")) {
				Configuration.roverProperties.getDeployPoint().setRow(updatedValue);
			} else {
				Configuration.roverProperties.getDeployPoint().setColumn(updatedValue);
			}
			Configuration.roverProperties.setInitialBattery(battery - 1);
			responseMessage = "Rover moved successfully";
		} else {
			if (!isImmobile) {
				responseMessage = "Cannot move due to scenario condition";
			} else {
				Configuration.roverDead = true;
				responseMessage = "Storm shield is not present";
			}
		}
		return responseMessage;
	}

	public boolean checkEnvironmentcondition(ScenarioCondition condition) {
		boolean conditionResult = false;
		RoverEnvironment environment = Configuration.roverStatus.getRoverEnvironment();
		Property property = condition.getProperty();
		switch (property) {
		case terrain:
			conditionResult = checkCondition(environment.getTerrain(), condition.getValue(), condition.getOperator());
			break;
		case temperature:
			conditionResult = checkCondition(environment.getTemperature(), condition.getValue(),
					condition.getOperator());
			break;
		case humidity:
			conditionResult = checkCondition(environment.getHumidity(), condition.getValue(), condition.getOperator());
			break;
		case solarFlare:
			conditionResult = checkCondition(environment.isSolarFlare(), condition.getValue(), condition.getOperator());
			break;
		case storm:
			conditionResult = checkCondition(environment.isStorm(), condition.getValue(), condition.getOperator());
			break;
		}
		return conditionResult;
	}

	public boolean checkCondition(Object value1, Object value2, Operator operator) {
		boolean conditionResult = false;
		switch (operator) {
		case eq:
			conditionResult = value1.equals(value2);
			break;
		case ne:
			conditionResult = !(value1.equals(value2));
			break;
		case lte:
			conditionResult = (int) value1 <= (int) value2;
			break;
		case gte:
			conditionResult = (int) value1 >= (int) value2;
			break;
		case gt:
			conditionResult = (int) value1 > (int) value2;
			break;
		case lt:
			conditionResult = (int) value1 < (int) value2;
			break;
		}
		return conditionResult;
	}
}
