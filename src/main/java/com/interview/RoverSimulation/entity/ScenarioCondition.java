package com.interview.RoverSimulation.entity;

import com.interview.RoverSimulation.enums.Operator;
import com.interview.RoverSimulation.enums.Property;
import com.interview.RoverSimulation.enums.ScenarioType;

public class ScenarioCondition {
	private ScenarioType type;

	private Property property;

	private Operator operator;

	private Object value;

	public ScenarioType getType() {
		return type;
	}

	public void setType(ScenarioType type) {
		this.type = type;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
