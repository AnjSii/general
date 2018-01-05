package com.wu.general.controller.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShiroController {

	//进入到登录页面
	@RequestMapping({ "/login.htm" })
	public String welcome(HttpServletRequest request, HttpServletResponse response) {
		return "login";
	}
}
