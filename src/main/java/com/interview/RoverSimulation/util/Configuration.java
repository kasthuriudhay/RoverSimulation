package com.interview.RoverSimulation.util;

import java.util.HashMap;
import java.util.Map;

import com.interview.RoverSimulation.entity.Environment;
import com.interview.RoverSimulation.entity.Rover;
import com.interview.RoverSimulation.entity.RoverStatus;
import com.interview.RoverSimulation.enums.InventoryItemType;

public class Configuration {
	
	public static Environment environmentProperties = new Environment();
	
	public static Rover roverProperties = new Rover();
	
	public static RoverStatus roverStatus = new RoverStatus();
	
	public static boolean roverDead = false;
	
	public static Map<InventoryItemType, Integer> samplesPriorityMap = new HashMap<>();
	static {
		samplesPriorityMap.put(InventoryItemType.StormShield,1);
		samplesPriorityMap.put(InventoryItemType.Watersample, 2);
		samplesPriorityMap.put(InventoryItemType.RockSample, 3);
		
	}

}
