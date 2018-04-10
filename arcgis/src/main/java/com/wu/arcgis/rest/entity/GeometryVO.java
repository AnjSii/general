package com.wu.arcgis.rest.entity;

import java.math.BigDecimal;
import java.util.List;

public class GeometryVO {

	private String type;

	private List<BigDecimal> coordinates;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<BigDecimal> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<BigDecimal> coordinates) {
		this.coordinates = coordinates;
	}
}