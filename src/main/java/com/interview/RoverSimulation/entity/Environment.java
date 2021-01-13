package com.interview.RoverSimulation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.RoverSimulation.enums.TerrainType;
import lombok.Data;

public class Environment {

	private int temperature;

	private int humidity;

	@JsonProperty("solar-flare")
	private boolean solarFlare;

	private boolean storm;

	@JsonProperty("area-map")
	private TerrainType[][] areaMap;

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	public boolean isSolarFlare() {
		return solarFlare;
	}

	public void setSolarFlare(boolean solarFlare) {
		this.solarFlare = solarFlare;
	}

	public boolean isStorm() {
		return storm;
	}

	public void setStorm(boolean storm) {
		this.storm = storm;
	}

	public TerrainType[][] getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(TerrainType[][] areaMap) {
		this.areaMap = areaMap;
	}

}
