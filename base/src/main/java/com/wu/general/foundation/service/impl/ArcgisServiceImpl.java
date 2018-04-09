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
import com.wu.general.foundation.domain.Arcgis;
import com.wu.general.foundation.service.ArcgisService;

@Service
@Transactional
public class ArcgisServiceImpl implements ArcgisService {

	@Resource (name = "arcgisDAO")
	private GenericDAO<Arcgis> arcgisDAO;

	@Override
	public boolean save(Arcgis arcgis) {
		try {
			this.arcgisDAO.save(arcgis);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.arcgisDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Arcgis arcgis) {
		try {
			this.arcgisDAO.update(arcgis);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Arcgis> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Arcgis> pList = new GenericPageList<>(Arcgis.class, properties, this.arcgisDAO);
		pList.doList();
		return pList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Arcgis getObjById(Long id) {
		return this.arcgisDAO.get(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Arcgis getObjByProperty(String propertyName, String value) {
		return this.arcgisDAO.getBy(propertyName, value);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Arcgis> query(String query, Map<String, Object> params, int begin, int max) {
		return this.arcgisDAO.query(query, params, begin, max);
	}
}
