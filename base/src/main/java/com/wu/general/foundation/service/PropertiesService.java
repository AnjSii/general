package com.wu.general.foundation.service;

import java.util.List;
import java.util.Map;

import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.Properties;

public interface PropertiesService {

	boolean save(Properties paramProperties);

	boolean delete(Long paramLong);

	boolean update(Properties paramProperties);

	PageList<Properties> list(QueryObject paramQueryObject);

	Properties getObjById(Long paramLong);

	Properties getObjByProperty(String paramString1,
							String paramString2);

	List<Properties> query(String paramString, Map<String, Object> paramMap,
					   int paramInt1, int paramInt2);
}
