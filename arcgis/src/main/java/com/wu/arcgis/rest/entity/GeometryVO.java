package com.wu.arcgis.rest.entity;

import java.util.List;

public class GeometryVO {

	private String type;

	private List<CoordinatesVO> coordinates;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<CoordinatesVO> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<CoordinatesVO> coordinates) {
		this.coordinates = coordinates;
	}
}