package com.wu.general.foundation.service.TestJPAPackageService;

import java.util.List;
import java.util.Map;

import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.Checkout;

/**
 * Created by Wu on 2017/8/3.
 */
public interface CheckoutService {

	boolean save(Checkout paramCheckout);

	boolean delete(Long paramLong);

	boolean update(Checkout paramCheckout);

	PageList<Checkout> list(QueryObject paramQueryObject);

	Checkout getObjById(Long paramLong);

	Checkout getObjByProperty(String paramString1,
							   String paramString2);

	List<Checkout> query(String paramString, Map<String, Object> paramMap,
						  int paramInt1, int paramInt2);
}
