package com.interview.RoverSimulation.entity;

import com.interview.RoverSimulation.enums.InventoryItemType;

public class ItemUsage {
	private InventoryItemType type;

	private int qty;

	public InventoryItemType getType() {
		return type;
	}

	public void setType(InventoryItemType type) {
		this.type = type;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	

}
