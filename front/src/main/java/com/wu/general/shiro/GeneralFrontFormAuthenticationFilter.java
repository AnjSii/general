package com.wu.general.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 认证过滤器
 */
public class GeneralFrontFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final String FORM_AJAX_LOGIN_KEY = "ajax_login";

	private static final String FORM_VERIFY_CODE_KEY = "code";

	private String ajaxLoginParameter = FORM_AJAX_LOGIN_KEY;

	private String captchaParameter = FORM_VERIFY_CODE_KEY;


	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
		String username = getUsername(request);
		String password = getPassword(request);
		if (password == null) {
			password = "";
		}
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		String captcha = getCaptcha(request);
		return createToken(username, password, captcha, rememberMe, host);
	}

	protected AuthenticationToken createToken(String username, String password, String captcha, boolean rememberMe, String host) {
		return new UsernamePasswordToken(username, password.toCharArray(), captcha, rememberMe, host);
	}

	public String getCaptchaParam() {
		return captchaParameter;
	}

	public void setCaptchaParam(String captchaParameter) {
		this.captchaParameter = captchaParameter;
	}

	protected String getCaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
		//判断来源，如果来自于Ajax则返回401，如果来自于普通同步请求则正常返回登录页面
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		if (httpServletRequest.getHeader("X-Requested-With") != null) {
			responseUnauthorizedState(httpServletRequest, httpServletResponse);
		} else {
			saveRequest(request);
			redirectToLogin(httpServletRequest, httpServletResponse);
		}
	}

	private void responseUnauthorizedState(ServletRequest request, ServletResponse response) {
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		httpServletResponse.setStatus(401);
	}
}
