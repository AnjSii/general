package com.wu.general.foundation.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wu.general.core.domain.IdEntity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Cache (usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table (name = "checkout")
public class Checkout extends IdEntity {

	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	private Long checkout_store_id;

	private Date checkoutTime;

	private int audit_status;

	private long order_count;

	private Date auditTime;

	private Long payment_id;

	private int checkout_status;

	@Column (precision = 12, scale = 2)
	private BigDecimal totalPrice;

	public Long getCheckout_store_id() {
		return checkout_store_id;
	}

	public void setCheckout_store_id(Long checkout_store_id) {
		this.checkout_store_id = checkout_store_id;
	}

	public Date getCheckoutTime() {
		return checkoutTime;
	}

	public void setCheckoutTime(Date checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public int getAudit_status() {
		return audit_status;
	}

	public void setAudit_status(int audit_status) {
		this.audit_status = audit_status;
	}

	public long getOrder_count() {
		return order_count;
	}

	public void setOrder_count(long order_count) {
		this.order_count = order_count;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Long getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(Long payment_id) {
		this.payment_id = payment_id;
	}

	public int getCheckout_status() {
		return checkout_status;
	}

	public void setCheckout_status(int checkout_status) {
		this.checkout_status = checkout_status;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
}
