package com.wu.general.shiro;

import javax.inject.Inject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wu.general.foundation.domain.User;
import com.wu.general.foundation.service.shiroService.ClientService;
import com.wu.general.foundation.service.shiroService.UserService;


/**
 * 前端用户认证、授权Realm
 *
 * @author zlei
 */
public class GeneralFrontAuthorizingRealm extends AuthorizingRealm {
	private static final Logger log = LoggerFactory.getLogger(GeneralFrontAuthorizingRealm.class);

	@Inject
	private UserService userService;

	@Inject
	private ClientService clientService;

	/* 认证 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		// 检查client登录失败次数，在一定时间内达到一定次数时，需要启用验证码
		boolean excessiveLoginAttempt = clientService.isExcessiveLoginAttempt(token.getHost());
		if (excessiveLoginAttempt) {
			// 判断验证码
			Session session = SecurityUtils.getSubject().getSession();
			String code = (String) session.removeAttribute("verify_code");
			if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code))
				throw new ExcessiveAttemptsException("验证码错误.");
		}

		if (token.getUsername() == null || token.getUsername().isEmpty())
			throw new UnknownAccountException("用户名为空");

		if (token.getPassword() == null || token.getPassword().length == 0)
			throw new CredentialsException("密码为空");


		User user = userService.getUserByUsername(token.getUsername());
		if (user == null)
			throw new UnknownAccountException("User " + token.getUsername()
					+ " not found");

		// xluo  这里并不是找不到用户  而是禁止管理员在前台登录
		if (user.getUserRole().equalsIgnoreCase("ADMIN"))
			throw new UnknownAccountException("User " + token.getUsername()
					+ " has invalid roles");


		// 帐号禁用检查, by hzl 2016/3/1, DisabledAccountException
		if (!user.isEnabled())
			throw new DisabledAccountException(user.getUserName() + " is disabled");

		// 帐号锁定检查, by hzl 2016/3/1, LockedAccountException
		if (!user.isAccountNonLocked())
			throw new LockedAccountException(user.getUserName() + " is locked");

		// 密码过期检查, by hzl 2016/3/1, ExpiredCredentialsException
		if (!user.isCredentialsNonExpired())
			throw new ExpiredCredentialsException(user.getUserName() + " credentials is expired");


		return new SimpleAuthenticationInfo(user.getId(), user.getPassword(), getName());
		/*
		if (manageUser != null) {
			byte[] salt = Encodes.decodeHex(manageUser.getPassword().substring(0,16));
			return new SimpleAuthenticationInfo(new Principal(manageUser),
					manageUser.getPassword().substring(16), ByteSource.Util.bytes(salt), getName());
		} else {
			return null;
		}
		*/
	}

	/* 授权 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Long userId = (Long) getAvailablePrincipal(principals);
		User user = userService.getObjById(userId);

		if (user != null) {
			String[] roles = user.getUserRole() == null ? null : user.getUserRole().split("_");

			if (roles == null)
				return null;

			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			for (String role : roles) {
				String r = role.trim().toUpperCase();
				if (r.length() > 0)
					info.addRole(r);
			}

			return info;
		}

		return null;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
