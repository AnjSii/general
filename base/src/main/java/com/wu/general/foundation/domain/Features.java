package com.wu.general.foundation.domain;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wu.general.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "arc_features")
public class Features extends IdEntity {

	private String type;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Properties properties;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Geometry geometry;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Arcgis arcgis;

	public void setType(String type) {
		this.type = type;
	 }

	public String getType() {
		return type;
	 }

	public void setProperties(Properties properties) {
		this.properties = properties;
	 }

	public Properties getProperties() {
		return properties;
	 }

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	 }

	public Geometry getGeometry() {
		return geometry;
	 }

	public Arcgis getArcgis() {
		return arcgis;
	}

	public void setArcgis(Arcgis arcgis) {
		this.arcgis = arcgis;
	}
}