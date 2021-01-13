package com.interview.RoverSimulation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.RoverSimulation.enums.Action;

public class State {
	
	private String name;
	
	private Action[] allowedActions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Action[] getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(Action[] allowedActions) {
		this.allowedActions = allowedActions;
	}
	
}
