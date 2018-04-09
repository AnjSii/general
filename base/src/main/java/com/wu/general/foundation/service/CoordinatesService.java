package com.wu.general.foundation.service;

import java.util.List;
import java.util.Map;

import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.Coordinates;

public interface CoordinatesService {

	boolean save(Coordinates paramCoordinates);

	boolean delete(Long paramLong);

	boolean update(Coordinates paramCoordinates);

	PageList<Coordinates> list(QueryObject paramQueryObject);

	Coordinates getObjById(Long paramLong);

	Coordinates getObjByProperty(String paramString1,
							String paramString2);

	List<Coordinates> query(String paramString, Map<String, Object> paramMap,
					   int paramInt1, int paramInt2);
}
