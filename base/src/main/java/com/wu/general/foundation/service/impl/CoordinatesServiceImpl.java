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
import com.wu.general.foundation.domain.Coordinates;
import com.wu.general.foundation.service.CoordinatesService;

@Service
@Transactional
public class CoordinatesServiceImpl implements CoordinatesService {

	@Resource (name = "coordinatesDAO")
	private GenericDAO<Coordinates> coordinatesDAO;

	@Override
	public boolean save(Coordinates coordinates) {
		try {
			this.coordinatesDAO.save(coordinates);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.coordinatesDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Coordinates coordinates) {
		try {
			this.coordinatesDAO.update(coordinates);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<Coordinates> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<Coordinates> pList = new GenericPageList<>(Coordinates.class, properties, this.coordinatesDAO);
		pList.doList();
		return pList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Coordinates getObjById(Long id) {
		return this.coordinatesDAO.get(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Coordinates getObjByProperty(String propertyName, String value) {
		return this.coordinatesDAO.getBy(propertyName, value);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Coordinates> query(String query, Map<String, Object> params, int begin, int max) {
		return this.coordinatesDAO.query(query, params, begin, max);
	}
}
