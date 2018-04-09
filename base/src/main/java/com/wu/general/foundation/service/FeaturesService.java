package com.wu.general.foundation.service;

import java.util.List;
import java.util.Map;

import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.Features;

/**
 * Created by Wu on 2017/8/3.
 */
public interface FeaturesService {

	boolean save(Features paramFeatures);

	boolean delete(Long paramLong);

	boolean update(Features paramFeatures);

	PageList<Features> list(QueryObject paramQueryObject);

	Features getObjById(Long paramLong);

	Features getObjByProperty(String paramString1,
							String paramString2);

	List<Features> query(String paramString, Map<String, Object> paramMap,
					   int paramInt1, int paramInt2);
}
