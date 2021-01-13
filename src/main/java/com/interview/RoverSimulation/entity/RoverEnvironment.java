package com.interview.RoverSimulation.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interview.RoverSimulation.enums.TerrainType;

public class RoverEnvironment {
	private int temperature;

	private int humidity;

	@JsonProperty("solar-flare")
	private boolean solarFlare;

	private boolean storm;

	private TerrainType terrain;

	public RoverEnvironment(int temperature, int humidity, boolean solarFlare, boolean storm, TerrainType terrain) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.solarFlare = solarFlare;
		this.storm = storm;
		this.terrain = terrain;
	}

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

	public TerrainType getTerrain() {
		return terrain;
	}

	public void setTerrain(TerrainType terrain) {
		this.terrain = terrain;
	}

}
