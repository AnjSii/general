package com.wu.general.foundation.domain;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wu.general.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "arc_properties")
public class Properties extends IdEntity {

	private String name;

	@Column(precision = 12, scale = 2)
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