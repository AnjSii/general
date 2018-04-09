package com.wu.arcgis.rest.entity;

import java.math.BigDecimal;

public class PropertiesVO {

	private String name;

	private BigDecimal waterLevel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(BigDecimal waterLevel) {
		this.waterLevel = waterLevel;
	}
}