package com.wu.general.core.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import com.wu.general.core.dao.GenericDAO;

public abstract class AbstractGenericDAO<T> implements GenericDAO<T> {
	protected Class<T> entityClass;

	@Inject
	@Named("genericEntityDao")
	private GenericEntityDao<T> geDao;

	public Class<T> getEntityClass() {
		return this.entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public GenericEntityDao<T> getGeDao() {
		return this.geDao;
	}

	public void setGeDao(GenericEntityDao<T> geDao) {
		this.geDao = geDao;
	}

	@SuppressWarnings("unchecked")
	public AbstractGenericDAO() {
		this.entityClass = (Class<T>) ((java.lang.reflect.ParameterizedType) super
				.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public AbstractGenericDAO(Class<T> type) {
		this.entityClass = type;
	}

	public int batchUpdate(String jpql, Object[] params) {
		return this.geDao.batchUpdate(jpql, params);
	}

	public List<T> executeNamedQuery(String queryName,
			Map<String, Object> params, int begin, int max) {
		return this.geDao.executeNamedQuery(this.entityClass, queryName,
				params, begin, max);
	}

	public List executeNativeNamedQuery(String nnq) {
		return this.geDao.executeNativeNamedQuery(nnq);
	}

	public List executeNativeQuery(String nnq, Object[] params, int begin,
			int max) {
		return this.geDao.executeNativeQuery(nnq, params, begin, max);
	}

	public int executeNativeSQL(String nnq) {
		return this.geDao.executeNativeSQL(nnq);
	}

	public List<T> find(String query, Map<String, String> joins, Map<String, Object> params, int begin, int max) {
		return getGeDao().find(this.entityClass, joins, query, params, begin, max);
	}

	public List<T> find(String query, Map<String, Object> params, int begin,
			int max) {
		return getGeDao().find(this.entityClass, query, params, begin, max);
	}

	public void flush() {
		this.geDao.flush();
	}

	public void refresh(T instance) {
		this.geDao.refresh(instance);
	}

	public T get(Serializable id) {
		return getGeDao().get(this.entityClass, id);
	}

	public T getBy(String propertyName, Object value) {
		return getGeDao().getBy(this.entityClass, propertyName, value);
	}

	public List query(String query, Map<String, Object> params, int begin, int max) {
		return getGeDao().query(query, params, begin, max);
	}

	public void remove(Serializable id) {
		getGeDao().remove(this.entityClass, id);
	}

	public void save(T newInstance) {
		getGeDao().save(newInstance);
	}

	public void update(T transientObject) {
		getGeDao().update(transientObject);
	}
}
