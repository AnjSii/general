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
import com.wu.general.foundation.domain.Geometry;
import com.wu.general.foundation.service.GeometryService;

@Service
@Transactional
public class GeometryServiceImpl implements GeometryService {

	@Resource (name = "geometryDAO")
	private GenericDAO<Geometry> geometryDAO;

	@Override
	public boolean save(Geometry geometry) {
		try {
			this.geometryDAO.save(geometry);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.geometryDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Geometry geometry) {
		try {
			this.geometryDAO.update(geometry);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Geometry> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Geometry> pList = new GenericPageList<>(Geometry.class, properties, this.geometryDAO);
		pList.doList();
		return pList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Geometry getObjById(Long id) {
		return this.geometryDAO.get(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Geometry getObjByProperty(String propertyName, String value) {
		return this.geometryDAO.getBy(propertyName, value);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Geometry> query(String query, Map<String, Object> params, int begin, int max) {
		return this.geometryDAO.query(query, params, begin, max);
	}
}
