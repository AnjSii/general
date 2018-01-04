package com.wu.general.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;


/**
 * 友好处理Ajax请求的UserFilter
 *
 * @author Xiangchun Zeng
 *
 */
public class GeneralUserFilter extends UserFilter {

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
