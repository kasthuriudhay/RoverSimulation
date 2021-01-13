package com.interview.RoverSimulation.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Action {
	move, 
	@JsonProperty("collect-sample")
	collectSample
	
}
