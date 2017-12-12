package com.wu.general.controller.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Welcome {
	@RequestMapping({ "/welcome.htm" })
	public String welcome(Model model) {
		return "welcome";
	}
}
