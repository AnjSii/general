package com.wu.general.foundation.service;

import java.util.List;
import java.util.Map;

import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.Arcgis;

public interface ArcgisService {

	boolean save(Arcgis paramArcgis);

	boolean delete(Long paramLong);

	boolean update(Arcgis paramArcgis);

	PageList<Arcgis> list(QueryObject paramQueryObject);

	Arcgis getObjById(Long paramLong);

	Arcgis getObjByProperty(String paramString1,
							  String paramString2);

	List<Arcgis> query(String paramString, Map<String, Object> paramMap,
						 int paramInt1, int paramInt2);
}
