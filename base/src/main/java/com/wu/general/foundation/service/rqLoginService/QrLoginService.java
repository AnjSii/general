package com.wu.general.foundation.service.rqLoginService;

import java.util.List;
import java.util.Map;

import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.QrLogin;

/**
 * Created by Wu on 2017/12/13.
 */
public interface QrLoginService {

	boolean save(QrLogin paramQrLogin);

	boolean delete(Long paramLong);

	boolean update(QrLogin paramQrLogin);

	PageList<QrLogin> list(QueryObject paramQueryObject);

	QrLogin getObjById(Long paramLong);

	QrLogin getObjBySid(String paramString);

	QrLogin getObjByProperty(String paramString1, String paramString2);

	List<QrLogin> query(String paramString, Map<String, Object> paramMap,
						 int paramInt1, int paramInt2);
}
