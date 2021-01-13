package com.interview.RoverSimulation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PerformCondition {
	
	@JsonProperty("collect-sample")
	private CollectSample collectSample;
	
	@JsonProperty("item-usage")
	private ItemUsage itemUsage;

	public CollectSample getCollectSample() {
		return collectSample;
	}

	public void setCollectSample(CollectSample collectSample) {
		this.collectSample = collectSample;
	}

	public ItemUsage getItemUsage() {
		return itemUsage;
	}

	public void setItemUsage(ItemUsage itemUsage) {
		this.itemUsage = itemUsage;
	}
	
}
