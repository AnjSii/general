package com.wu.general.shiro;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.PrincipalCollection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wu.general.foundation.domain.User;
import com.wu.general.foundation.service.shiroService.ClientService;
import com.wu.general.foundation.service.shiroService.UserService;

/**
 * Created by zlei on 3/1/16.
 */
public class GeneralAuthenticationListener implements AuthenticationListener {
	private static final Logger log = LoggerFactory.getLogger(GeneralAuthenticationListener.class);

	@Inject
	private UserService userService;

	@Inject
	private ClientService clientService;


	public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
		if (org.apache.shiro.authc.UsernamePasswordToken.class.isAssignableFrom(token.getClass())) {
			User user = userService.getUserByUsername(((org.apache.shiro.authc.UsernamePasswordToken) token).getUsername());
			if (user != null) {
				// TODO: 记录用户登录ip,登录时间
			}
		}
	}

	public void onFailure(AuthenticationToken token, AuthenticationException ae) {
		if (org.apache.shiro.authc.UsernamePasswordToken.class.isAssignableFrom(token.getClass())) {
			org.apache.shiro.authc.UsernamePasswordToken tk = (org.apache.shiro.authc.UsernamePasswordToken) token;

			clientService.increaseClientLoginFailCount(tk.getHost());
			log.debug("帐号 {} 登录失败, client {}", tk.getUsername(), tk.getHost());

			// 当某个帐号在连续登录失败一定次数时，锁定帐号，并发邮件提示用户帐号风险 by hzl 2016/3/1
			if (ae instanceof IncorrectCredentialsException) {
				User user = userService.getUserByUsername(tk.getUsername());

				// TODO: 当某个帐号在连续登录失败一定次数时，锁定帐号，并发邮件提示用户帐号风险 by hzl 2016/3/1
				if (user != null) {
					// TODO: increase account fail count
					// TODO: check total fail count
					// TODO: lock user account 5 minutes if fail count > 15, notify user by email
					// TODO: lock user acount 10 minutes on later failures
				}
			}
		}
	}

	public void onLogout(PrincipalCollection principals) {
	}
}
