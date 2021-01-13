package com.interview.RoverSimulation.entity;

import java.util.Optional;

public class RoverCondition {
	
	private String is;
	
	private PerformCondition performs;
	
	public RoverCondition(String is) {
		this.is=is;
		this.performs= new PerformCondition();
	}
	
	public RoverCondition(PerformCondition performs) {
		this.is=null;
		this.performs=performs;
	}
	
	public RoverCondition(String is, PerformCondition performs) {
		this.is=is;
		this.performs=performs;
	}
	
	public RoverCondition() {
		this.is=null;
		this.performs=new PerformCondition();
	}
	

	public String getIs() {
		return is;
	}

	public void setIs(String is) {
		this.is = is;
	}

	public PerformCondition getPerforms() {
		return performs;
	}

	public void setPerforms(PerformCondition performs) {
		this.performs = performs;
	}
	
	
}

