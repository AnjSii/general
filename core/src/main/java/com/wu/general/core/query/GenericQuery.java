package com.wu.general.core.query;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wu.general.core.dao.GenericDAO;
import com.wu.general.core.query.support.Query;

public class GenericQuery<T> implements Query<T> {
	private final GenericDAO<T> dao;

	private int begin;

	private int max;

	private final Map<String, Object> params;

	public GenericQuery(GenericDAO<T> dao, Map<String, Object> params) {
		if (params == null)
			params = Collections.emptyMap();
		else
			params = new HashMap<>(params);

		this.dao = dao;
		this.params = params;
	}

	public List<T> getResult(String condition, Map<String, String> joins) {
		return this.dao.find(condition, joins, this.params, this.begin, this.max);
	}

	public List<T> getResult(String condition, int begin, int max) {
		return this.dao.find(condition, this.params, begin, max);
	}

	public long getRows(String condition) {
		int n = condition.toLowerCase().indexOf("order by");
		if (n > 0) {
			condition = condition.substring(0, n);
		}
		List ret = this.dao.query(condition, this.params, 0, 0);
		if ((ret != null) && (ret.size() > 0)) {
			return ((Long) ret.get(0)).longValue();
		}
		return 0;
	}

	public void setFirstResult(int begin) {
		this.begin = begin;
	}

	public void setMaxResults(int max) {
		this.max = max;
	}
}
