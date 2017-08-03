package com.wu.general.core.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.wu.general.core.exception.CanotRemoveObjectException;

//import org.hibernate.annotations.QueryHints;

public class GenericEntityDao<E> {

	@PersistenceContext
	protected EntityManager entityManager;

	public E get(Class<E> clazz, Serializable id) {
		if (id == null)
			return null;
		return entityManager.find(clazz, id);
	}

	public List<E> find(final Class<E> clazz, final String queryStr, final Map<String, Object> params, final int begin, final int max) {
		return find(clazz, Collections.emptyMap(), queryStr, params, begin, max);
	}

	public List<E> find(final Class<E> clazz, final Map<String, String> joins, final String queryStr, final Map<String, Object> params,
						final int begin, final int max) {
		String clazzName = clazz.getName();
		StringBuffer sb = new StringBuffer("select obj from ");
		sb.append(clazzName).append(" obj");

		for (Entry<String, String> entry : joins.entrySet()) {
			String attri = entry.getKey();
			String alias = entry.getValue();

			sb.append(" join ").append(attri).append(" as ").append(alias);
		}

		sb.append(" where ").append(queryStr);
		TypedQuery<E> query = entityManager.createQuery(sb.toString(), clazz);
		for (Iterator<String> localIterator = params.keySet()
				.iterator(); localIterator.hasNext();) {
			String key = localIterator.next();
			query.setParameter(key, params.get(key));
		}
		if ((begin >= 0) && (max > 0)) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		setQueryCache(query);
		List<E> ret = query.getResultList();
		if (ret != null && ret.size() >= 0) {
			return ret;
		}
		return new ArrayList<>();
	}

	public List<?> query(final String  queryStr, final Map<String, Object> params, final int begin, final int max) {
		Query query = entityManager.createQuery(queryStr);
		if ((params != null) && (params.size() > 0)) {
			for (Iterator<String> localIterator = params
					.keySet().iterator(); localIterator
					.hasNext();) {
				String key = localIterator.next();
				query.setParameter(key, params.get(key));
			}
		}
		if ((begin >= 0) && (max > 0)) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		setQueryCache(query);
		List<?> list = query.getResultList();
		if ((list != null) && (list.size() > 0)) {
			return list;
		}
		return new ArrayList<>();
	}

	public void remove(Class<E> clazz, Serializable id)
			throws CanotRemoveObjectException {
		E object = get(clazz, id);
		remove(object);
	}

	public void remove(E instance)
			throws CanotRemoveObjectException {
		if (instance == null)
			return;
		try {
			entityManager.remove(instance);
		} catch (Exception e) {
			throw new CanotRemoveObjectException();
		}
	}

	public void save(E instance) {
		entityManager.persist(instance);
	}

	public E getBy(final Class<E> clazz, final String propertyName, final Object value) {
		String clazzName = clazz.getName();
		StringBuffer sb = new StringBuffer("select obj from ");
		sb.append(clazzName).append(" obj");
		TypedQuery<E> query = null;
		if ((propertyName != null)
				&& (value != null)) {
			sb.append(" where obj.")
					.append(propertyName)
					.append(" = :value");
			query = entityManager.createQuery(sb.toString(), clazz).setParameter(
					"value", value);
		} else {
			query = entityManager.createQuery(sb.toString(), clazz);
		}
		setQueryCache(query);
		List<E> ret = query.getResultList();
		if ((ret != null) && (ret.size() == 1))
			return ret.get(0);
		if ((ret != null) && (ret.size() > 1)) {
			throw new IllegalStateException(
					"worning  --more than one object find!!");
		}
		return null;
	}

	public List<E> executeNamedQuery(final Class<E> clazz, final String queryName,
			final Map<String, Object> params, final int begin, final int max) {
		TypedQuery<E> query = entityManager.createNamedQuery(queryName, clazz);
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet()) {
				query.setParameter(e.getKey(), e.getValue());
			}
		}
		if ((begin >= 0) && (max > 0)) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}
		setQueryCache(query);
		List<E> ret = query.getResultList();
		if ((ret != null) && (ret.size() >= 0)) {
			return ret;
		}
		return new ArrayList<>();
	}

	public void update(E instance) {
		entityManager.merge(instance);
	}

	public List<?> executeNativeNamedQuery(final String nnq) {
		Query query = entityManager.createNativeQuery(nnq);
		List<?> ret =  query.getResultList();
		return ret;
	}

	public List<?> executeNativeQuery(final String nnq, final Map<String, Object> params, final int begin, final int max) {
		Query query = entityManager.createNativeQuery(nnq);
		//int parameterIndex = 1;
		if (params != null) {
			for (Entry<String, Object> e : params.entrySet()) {
				String k = e.getKey();
				Object v = e.getValue();
				if (k != null)
					query.setParameter(k, v);
			}
		}
		if ((begin >= 0) && (max > 0)) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}

		List<?> ret = query.getResultList();
		if ((ret != null) && (ret.size() >= 0)) {
			return ret;
		}
		return new ArrayList<>();
	}

	public List<?> executeNativeQuery(final String nnq, final Object[] params, final int begin,
			final int max) {
		Query query = entityManager.createNativeQuery(nnq);
		int parameterIndex = 1;
		if ((params != null)
				&& (params.length > 0)) {
			for (Object obj : params) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		if ((begin >= 0) && (max > 0)) {
			query.setFirstResult(begin);
			query.setMaxResults(max);
		}

		List<?> ret =  query.getResultList();
		if ((ret != null) && (ret.size() >= 0)) {
			return ret;
		}
		return new ArrayList<>();
	}

	public int executeNativeSQL(final String nnq) {
		Query query = entityManager.createNativeQuery(nnq);
		setQueryCache(query);
		int ret = query.executeUpdate();
		return ret;
	}

	public int batchUpdate(final String jpql, final Object[] params) {
		Query query = entityManager.createQuery(jpql);
		int parameterIndex = 1;
		if ((params != null) && (params.length > 0)) {
			for (Object obj : params) {
				query.setParameter(parameterIndex++, obj);
			}
		}
		setQueryCache(query);
		int ret = query.executeUpdate();
		return ret;
	}

	public void flush() {
		//FIXME should be entityManager.flush() ???, by hzl 2015/5/25
		//entityManager.getTransaction().commit();
		entityManager.flush();
	}

	public void refresh(E instance) {
		entityManager.refresh(instance);
	}

	public <T> boolean isLoaded(T instance) {
		return entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(instance);
	}

	public <T> boolean isLoaded(T instance, String attr) {
		return entityManager.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(instance, attr);
	}

	private static void setQueryCache(Query query) {
		query.setHint("org.hibernate.cacheable", true);
		//query.setHint(QueryHints.CACHEABLE, true);
	}
}
