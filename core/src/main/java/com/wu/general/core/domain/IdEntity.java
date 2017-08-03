package com.wu.general.core.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.wu.general.core.annotation.Lock;

/**
 * Created by Wu on 2017/8/3.
 */
@MappedSuperclass
public class IdEntity implements Serializable {

	@Id
	/*通过annotation来映射hibernate实体的,基于annotation的hibernate主键标识为@Id,自增长*/
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private  Long id;

	/**
	 * 添加时间
	 */
	private Date addTime;

	/**
	 * 删除状态 0正常，1删除（逻辑删除标识）
	 */
	@Lock
	private boolean deleteStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public boolean isDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(boolean deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
}
