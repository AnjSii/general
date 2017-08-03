package com.wu.general.foundation.service.impl.TestJPAPackageServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wu.general.core.dao.GenericDAO;
import com.wu.general.core.query.GenericPageList;
import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.Checkout;
import com.wu.general.foundation.service.TestJPAPackageService.CheckoutService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Wu on 2017/8/3.
 */
@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {

	@Resource (name = "checkoutDAO")
	private GenericDAO<Checkout> checkoutDAO;

	@Override
	public boolean save(Checkout checkout) {
		try {
			this.checkoutDAO.save(checkout);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.checkoutDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Checkout checkout) {
		try {
			this.checkoutDAO.update(checkout);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Checkout> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Checkout> pList = new GenericPageList<>(Checkout.class, properties, this.checkoutDAO);
		pList.doList();
		return pList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Checkout getObjById(Long id) {
		return this.checkoutDAO.get(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Checkout getObjByProperty(String propertyName, String value) {
		return this.checkoutDAO.getBy(propertyName, value);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Checkout> query(String query, Map<String, Object> params, int begin, int max) {
		return this.checkoutDAO.query(query, params, begin, max);
	}
}
