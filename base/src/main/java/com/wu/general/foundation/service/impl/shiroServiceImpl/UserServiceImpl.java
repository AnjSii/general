package com.wu.general.foundation.service.impl.shiroServiceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wu.general.core.dao.GenericDAO;
import com.wu.general.core.query.GenericPageList;
import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.User;
import com.wu.general.foundation.service.shiroService.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource(name = "userDAO")
	private GenericDAO<User> userDAO;

	@Value ("${assets.url}")
	private String miscWebServer;

	@Value ("${front.url}")
	private String frontUrl;

	public boolean delete(Long id) {
		try {
			this.userDAO.remove(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public User getObjById(Long id) {
		return this.userDAO.get(id);
	}

	public boolean save(User user) {
		try {
			this.userDAO.save(user);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	public boolean update(User user) {
		try {
			this.userDAO.update(user);
			return true;
		} catch (Exception ignore) {
		}
		return false;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<User> query(String query, Map<String, Object> params,
							int begin, int max) {
		return this.userDAO.query(query, params, begin, max);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public PageList<User> list(QueryObject properties) {
		if (properties == null) {
			return null;
		}
		PageList<User> pList = new GenericPageList<>(User.class, properties,
				this.userDAO);
		pList.doList();
		return pList;
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public User getObjByProperty(String propertyName, String value) {
		return this.userDAO.getBy(propertyName, value);
	}

	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public User getUserByUsername(String username) {
		return userDAO.getBy("userName", username);
	}
}
