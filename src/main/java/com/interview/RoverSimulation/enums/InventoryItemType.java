package com.interview.RoverSimulation.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum InventoryItemType {
	@JsonProperty("storm-shield")
	StormShield, 
	@JsonProperty("water-sample")
	Watersample,
	@JsonProperty("rock-sample")
	RockSample

}
