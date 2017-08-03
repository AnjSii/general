package com.wu.general.core.query;

import java.util.Map;

import com.wu.general.core.dao.GenericDAO;
import com.wu.general.core.query.support.QueryObject;

public class GenericPageList<T> extends AbstractPageList<T> {
	private static final long serialVersionUID = 2L;

	protected final Map<String, String> joins;

	protected final String scope;

	protected final Class<T> cls;

	protected final PageObject pageObject;

	public GenericPageList(Class<T> cls, QueryObject queryObject, GenericDAO<T> dao) {
		super(new GenericQuery<>(dao, queryObject.getParameters()));
		this.cls = cls;
		this.joins = queryObject.getJoins();
		this.scope = queryObject.getQuery();
		this.pageObject = queryObject.getPageObj();
	}

	public void doList() {
		if (pageObject == null || pageObject.getPageSize() == null || pageObject.getPageSize() <= 0) {
			super.doList(getTotalSql(), scope, joins);
		} else {
			super.doList(getTotalSql(), scope, joins, pageObject.getPageSize(), pageObject.getCurrentPage());
		}
	}

	public void doList(int currentPage, int pageSize) {
		if (currentPage <= 0 || pageSize <= 0)
			throw new IllegalArgumentException("invalid parameter");

		super.doList(getTotalSql(), scope, joins, pageSize, currentPage);
	}

	protected String getTotalSql() {
		String totalSql = "select COUNT(obj) from " + this.cls.getName() + " obj ";

		if (!joins.isEmpty()) {
			totalSql += buildJoins();
		}

		totalSql += " where " + this.scope;
		return totalSql;
	}

	private String buildJoins() {
		StringBuilder sb = new StringBuilder();

		for (Map.Entry<String, String> entry : joins.entrySet()) {
			String attri = entry.getKey();
			String alias = entry.getValue();

			sb.append("join ").append(attri).append(" as ").append(alias);
		}
		return sb.toString();
	}
}
