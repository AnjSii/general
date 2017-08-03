package com.wu.general.core.query.support;

import java.util.List;
import java.util.Map;

public interface Query<T> {
	long getRows(String paramString);

	List<T> getResult(String paramString, Map<String, String> joins);

	void setFirstResult(int paramInt);

	void setMaxResults(int paramInt);
}
