package com.wu.general.foundation.service.shiroService;

import java.util.List;
import java.util.Map;

import com.wu.general.core.query.support.PageList;
import com.wu.general.core.query.support.QueryObject;
import com.wu.general.foundation.domain.User;

public interface UserService {
	boolean save(User paramUser);

	boolean delete(Long paramLong);

	boolean update(User paramUser);

	PageList<User> list(QueryObject paramQueryObject);

	User getObjById(Long paramLong);

	User getObjByProperty(String paramString1,
						  String paramString2);

	List<User> query(String paramString, Map<String, Object> paramMap,
					 int paramInt1, int paramInt2);

	User getUserByUsername(String username);
}
