package com.wu.general.shiro;

import javax.inject.Inject;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationListener;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wu.general.foundation.domain.QrLogin;
import com.wu.general.foundation.service.rqLoginService.QrLoginService;

/**
 * 二维码登录认证的Listener
 */
public class GeneralQrCodeAuthenticationListener implements AuthenticationListener {
	private static final Logger log = LoggerFactory.getLogger(GeneralQrCodeAuthenticationListener.class);

	@Inject
	private QrLoginService qrService;

	public void onSuccess(AuthenticationToken token, AuthenticationInfo info) {
		if (SidToken.class.isAssignableFrom(token.getClass())) {
			//登录成功后，将sid的状态标记为登录成功
			QrLogin qrcode =  qrService.getObjBySid(new String(((SidToken)token).getSid()));
			if (qrcode != null) {
				/*qrcode.setLogin(true);
				qrService.save(qrcode);*/
			}
		}
	}

	public void onFailure(AuthenticationToken token, AuthenticationException ae) {
	}

	public void onLogout(PrincipalCollection principals) {
	}
}
