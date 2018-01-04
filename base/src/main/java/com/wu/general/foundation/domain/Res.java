package com.wu.general.foundation.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.wu.general.core.domain.IdEntity;

@Cacheable
@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "shop_res")
public class Res extends IdEntity {
	private String resName;

	private String type;

	private String value;

	@ManyToMany(mappedBy = "reses", targetEntity = Role.class)
	@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
	private List<Role> roles = new ArrayList<>();

	private int sequence;

	private String info;

	@Transient
	public String getRoleAuthorities() {
		List<String> roleAuthorities = new ArrayList<>(roles.size());
		for (Role role : this.roles) {
			roleAuthorities.add(role.getRoleCode());
		}
		return StringUtils.join(roleAuthorities.toArray(), ",");
	}

	public String getResName() {
		return this.resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getSequence() {
		return this.sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
}
