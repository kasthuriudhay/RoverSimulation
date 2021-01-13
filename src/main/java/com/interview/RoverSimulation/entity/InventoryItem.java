package com.interview.RoverSimulation.entity;

import com.interview.RoverSimulation.enums.InventoryItemType;

public class InventoryItem {
	private InventoryItemType type;
	private int quantity;
	private int priority;
	public InventoryItem(InventoryItemType type, int quantity, Integer priority) {
		this.type=type;
		this.quantity=quantity;
		this.priority=priority;
	}
	public InventoryItemType getType() {
		return type;
	}
	public void setType(InventoryItemType type) {
		this.type = type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
