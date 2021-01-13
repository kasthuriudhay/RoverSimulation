package com.interview.RoverSimulation.entity;


public class Scenario {

	private String name;

	private ScenarioCondition[] conditions;

	private RoverCondition[] rover;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoverCondition[] getRover() {
		return rover;
	}

	public void setRover(RoverCondition[] rover) {
		this.rover = rover;
	}

	public ScenarioCondition[] getConditions() {
		return conditions;
	}

	public void setConditions(ScenarioCondition[] conditions) {
		this.conditions = conditions;
	}
	
}
