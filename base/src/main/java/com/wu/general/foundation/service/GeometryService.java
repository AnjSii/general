package com.wu.general.foundation.service;

import java.util.List;
import java.util.Map;

import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.Geometry;

public interface GeometryService {

	boolean save(Geometry paramGeometry);

	boolean delete(Long paramLong);

	boolean update(Geometry paramGeometry);

	PageList<Geometry> list(QueryObject paramQueryObject);

	Geometry getObjById(Long paramLong);

	Geometry getObjByProperty(String paramString1,
							String paramString2);

	List<Geometry> query(String paramString, Map<String, Object> paramMap,
					   int paramInt1, int paramInt2);
}
