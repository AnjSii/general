package com.wu.general.foundation.domain;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wu.general.core.domain.IdEntity;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "arc_arcgis")
public class Arcgis extends IdEntity {

	private String type;

	@OneToMany(mappedBy = "arcgis", cascade = { javax.persistence.CascadeType.REMOVE })
	private List<Features> features;

	public void setType(String type) {
		this.type = type;
	 }

	public String getType() {
		return type;
	 }

	public void setFeatures(List<Features> features) {
		this.features = features;
	 }

	public List<Features> getFeatures() {
		return features;
	 }

}