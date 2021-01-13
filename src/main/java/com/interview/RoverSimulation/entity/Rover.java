package com.interview.RoverSimulation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rover {
	
	private Scenario[] scenarios;
	
	private State[] states;
	
	@JsonProperty("deploy-point")
	private DeployPoint deployPoint;
	
	@JsonProperty("initial-battery")
	private int initialBattery;
	
	private InventoryItem[] inventory;
	
	public Scenario[] getScenarios() {
		return scenarios;
	}

	public void setScenarios(Scenario[] scenarios) {
		this.scenarios = scenarios;
	}

	public State[] getStates() {
		return states;
	}

	public void setStates(State[] states) {
		this.states = states;
	}

	public DeployPoint getDeployPoint() {
		return deployPoint;
	}

	public void setDeployPoint(DeployPoint deployPoint) {
		this.deployPoint = deployPoint;
	}

	public int getInitialBattery() {
		return initialBattery;
	}

	public void setInitialBattery(int initialBattery) {
		this.initialBattery = initialBattery;
	}

	public InventoryItem[] getInventory() {
		return inventory;
	}

	public void setInventory(InventoryItem[] inventory) {
		this.inventory = inventory;
	}

}
