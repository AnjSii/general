package com.wu.general.foundation.service.shiroService;

/**
 * 检查用户登录异常状态服务
 */
public interface ClientService {
	int getClientLoginFailCount(String host);

	void increaseClientLoginFailCount(String host);

	boolean isExcessiveLoginAttempt(String host);
}
