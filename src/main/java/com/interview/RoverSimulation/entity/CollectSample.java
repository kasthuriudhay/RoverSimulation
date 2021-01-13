package com.interview.RoverSimulation.entity;

import com.interview.RoverSimulation.enums.InventoryItemType;

public class CollectSample {
	private InventoryItemType type;
	
	private int qty;

	public InventoryItemType getType() {
		return type;
	}

	public void setType(InventoryItemType type) {
		this.type = type;
	}

	public int getQuantity() {
		return qty;
	}

	public void setQuantity(int quantity) {
		this.qty = quantity;
	}
	
	
}
