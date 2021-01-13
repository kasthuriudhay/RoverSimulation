package com.interview.RoverSimulation.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Property {
    
	battery, terrain, temperature, humidity, @JsonProperty("solar-flare")solarFlare, storm
}
