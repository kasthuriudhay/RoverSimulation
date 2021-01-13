package com.interview.RoverSimulation.entity;

public class RoverStatus {
	private DeployPoint location;
	
	private int battery;
	
	private InventoryItem[] inventory;
	
	private RoverEnvironment environment;

	public DeployPoint getLocation() {
		return location;
	}

	public void setLocation(DeployPoint location) {
		this.location = location;
	}

	public int getBattery() {
		return battery;
	}

	public void setBattery(int battery) {
		this.battery = battery;
	}

	public InventoryItem[] getInventoryItem() {
		return inventory;
	}

	public void setInventoryItem(InventoryItem[] inventoryItem) {
		this.inventory = inventoryItem;
	}

	public RoverEnvironment getRoverEnvironment() {
		return environment;
	}

	public void setRoverEnvironment(RoverEnvironment roverEnvironment) {
		this.environment = roverEnvironment;
	}
	
}
