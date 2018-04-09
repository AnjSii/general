package com.wu.general.foundation.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wu.general.core.dao.GenericDAO;
import com.wu.general.core.query.GenericPageList;
import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.Features;
import com.wu.general.foundation.service.FeaturesService;

@Service
@Transactional
public class FeaturesServiceImpl implements FeaturesService {

	@Resource (name = "featuresDAO")
	private GenericDAO<Features> featuresDAO;

	@Override
	public boolean save(Features features) {
		try {
			this.featuresDAO.save(features);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.featuresDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Features features) {
		try {
			this.featuresDAO.update(features);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Features> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Features> pList = new GenericPageList<>(Features.class, properties, this.featuresDAO);
		pList.doList();
		return pList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Features getObjById(Long id) {
		return this.featuresDAO.get(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Features getObjByProperty(String propertyName, String value) {
		return this.featuresDAO.getBy(propertyName, value);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Features> query(String query, Map<String, Object> params, int begin, int max) {
		return this.featuresDAO.query(query, params, begin, max);
	}
}
