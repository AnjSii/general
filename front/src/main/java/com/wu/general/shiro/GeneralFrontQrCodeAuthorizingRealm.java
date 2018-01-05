package com.wu.general.shiro;

import java.util.Date;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wu.general.foundation.domain.QrLogin;
import com.wu.general.foundation.domain.User;
import com.wu.general.foundation.service.rqLoginService.QrLoginService;
import com.wu.general.foundation.service.shiroService.UserService;

/**
 * 前端用户二维码登录认证、授权Realm
 */
public class GeneralFrontQrCodeAuthorizingRealm extends AuthorizingRealm {
	private static final Logger log = LoggerFactory.getLogger(GeneralFrontQrCodeAuthorizingRealm.class);

	@Inject
	private UserService userService;

	@Inject
	private QrLoginService loginQrCodeService;

	/* 认证 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		/*SidToken token = (SidToken) authcToken;
		QrLogin qrCode = loginQrCodeService.getObjBySid(new String(token.getSid()));

		if (qrCode == null)
			throw new IncorrectCredentialsException();

		if (new Date().getTime() > qrCode.getInvalidTime().getTime())
			throw new ExpiredCredentialsException();

		if (qrCode.getUserName() == null)
			throw new IncorrectCredentialsException();

		if (!qrCode.getAddIp().equals(token.getHost()))
			throw new AuthenticationException();

		if (qrCode.isLogin())
			throw new AuthenticationException();

		User user = userService.getUserByUsername(qrCode.getUserName());
		if (user == null)
			throw new UnknownAccountException("User " + qrCode.getUserName()
					+ " not found");

		// xluo  这里并不是找不到用户  而是禁止管理员在前台登录
		if (user.getUserRole().equalsIgnoreCase("ADMIN"))
			throw new UnknownAccountException("User " + qrCode.getUserName()
					+ " has invalid roles");


		// 帐号禁用检查, DisabledAccountException
		if (!user.isEnabled())
			throw new DisabledAccountException(user.getUsername() + " is disabled");

		// 帐号锁定检查, LockedAccountException
		if (!user.isAccountNonLocked())
			throw new LockedAccountException(user.getUsername() + " is locked");

		return new SimpleAuthenticationInfo(user.getId(), qrCode.getSid().toCharArray(), getName());*/
		return null;
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

	@Override
	public Class<?> getAuthenticationTokenClass() {
		return SidToken.class;
	}
}
