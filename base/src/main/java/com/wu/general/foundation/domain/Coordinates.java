package com.wu.general.foundation.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wu.general.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "arc_coordinates")
public class Coordinates extends IdEntity {

	@Column(precision = 12, scale = 2)
	private BigDecimal coordinates;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Geometry geometry;

	public BigDecimal getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(BigDecimal coordinates) {
		this.coordinates = coordinates;
	}

	public Geometry getGeometry() {
		return geometry;
	}

	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
}
