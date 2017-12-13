package com.wu.general.foundation.service.impl.rqLoginServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.wu.general.core.dao.GenericDAO;
import com.wu.general.core.query.GenericPageList;
import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.QrLogin;
import com.wu.general.foundation.service.rqLoginService.QrLoginService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Wu on 2017/8/3.
 */
@Service
@Transactional
public class QrLoginServiceImpl implements QrLoginService {

	@Resource (name = "qrLoginDAO")
	private GenericDAO<QrLogin> qrLoginDAO;

	@Override
	public boolean save(QrLogin qrLogin) {
		try {
			this.qrLoginDAO.save(qrLogin);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	public boolean delete(Long id) {
		try {
			this.qrLoginDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(QrLogin qrLogin) {
		try {
			this.qrLoginDAO.update(qrLogin);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<QrLogin> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<QrLogin> pList = new GenericPageList<>(QrLogin.class, properties, this.qrLoginDAO);
		pList.doList();
		return pList;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public QrLogin getObjById(Long id) {
		return this.qrLoginDAO.get(id);
	}

	@Override
	public QrLogin getObjBySid(String paramString) {
		return qrLoginDAO.getBy("sid", paramString);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public QrLogin getObjByProperty(String propertyName, String value) {
		return this.qrLoginDAO.getBy(propertyName, value);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<QrLogin> query(String query, Map<String, Object> params, int begin, int max) {
		return this.qrLoginDAO.query(query, params, begin, max);
	}
}
