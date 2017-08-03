package com.wu.general.controller.TestJPAPackage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wu.general.foundation.domain.Checkout;
import com.wu.general.foundation.service.TestJPAPackageService.CheckoutService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Wu on 2017/8/3.
 */
@Controller
public class TestJPAPackageController {

	@Autowired
	private CheckoutService checkoutService;

	@RequestMapping({ "/TestJPAPackageFindById.htm" })
	public String testJPAPackageFindById(Long id, Map<String, Object> map) {
		Checkout checkout = this.checkoutService.getObjById(id);
		map.put("checkout", checkout);
		return "TestJPAPackage";
	}

	@RequestMapping({ "/testJPAPackageSave.htm" })
	public String testJPAPackageSave(Map<String, Object> map) {
		Checkout checkout = new Checkout();
		checkout.setAudit_status(0);
		checkout.setAuditTime(new Date());
		checkout.setCheckout_status(0);
		checkout.setCheckout_store_id(1L);
		checkout.setCheckoutTime(new Date());
		checkout.setPayment_id((long) 23);
		checkout.setTotalPrice(new BigDecimal("1.00"));
		checkout.setOrder_count(1);
		boolean flag = this.checkoutService.save(checkout);
		map.put("boolean", flag);
		return "TestJPAPackage";
	}

	@RequestMapping({ "/testJPAPackageQueryAndDelete.htm" })
	public String TestJPAPackageQueryAndDelete(Long id, Map<String, Object> map) {
		List<Checkout> checkoutList = this.checkoutService
				.query("select obj from Checkout obj", null, -1, -1);
		Checkout checkout = checkoutList.get(checkoutList.size() - 1);
		boolean flag = this.checkoutService.delete(checkout.getId());
		map.put("boolean", flag);
		return "TestJPAPackage";
	}

	@RequestMapping({ "/testJPAPackageUpdate.htm" })
	public String TestJPAPackageUpdate(Long id, int count, Map<String, Object> map) {
		Checkout checkout = this.checkoutService.getObjById(id);
		checkout.setOrder_count(count);
		boolean flag = this.checkoutService.update(checkout);
		map.put("boolean", flag);
		return "TestJPAPackage";
	}
}
