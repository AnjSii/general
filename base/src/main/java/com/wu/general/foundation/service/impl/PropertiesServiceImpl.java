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
import com.wu.general.foundation.domain.Properties;
import com.wu.general.foundation.service.PropertiesService;

@Service
@Transactional
public class PropertiesServiceImpl implements PropertiesService {

	@Resource (name = "propertiesDAO")
	private GenericDAO<Properties> propertiesDAO;

	@Override
	public boolean save(Properties properties) {
		try {
			this.propertiesDAO.save(properties);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.propertiesDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Properties properties) {
		try {
			this.propertiesDAO.update(properties);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Properties> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Properties> pList = new GenericPageList<>(Properties.class, properties, this.propertiesDAO);
		pList.doList();
		return pList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Properties getObjById(Long id) {
		return this.propertiesDAO.get(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Properties getObjByProperty(String propertyName, String value) {
		return this.propertiesDAO.getBy(propertyName, value);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Properties> query(String query, Map<String, Object> params, int begin, int max) {
		return this.propertiesDAO.query(query, params, begin, max);
	}
}
