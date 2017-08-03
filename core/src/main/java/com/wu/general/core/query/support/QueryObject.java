package com.wu.general.core.query.support;

import java.util.Map;

import com.wu.general.core.domain.virtual.SysMap;
import com.wu.general.core.query.PageObject;

public interface QueryObject {
	String getQuery();

	Map<String, String> getJoins();

	Map<String, Object> getParameters();

	PageObject getPageObj();

	QueryObject join(String field, String alias);

	QueryObject addQuery(String paramString, Map<String, Object> paramMap);

	QueryObject addQuery(String field, SysMap param, String expression);

	QueryObject addQuery(String param, Object object, String field, String expression);
}
