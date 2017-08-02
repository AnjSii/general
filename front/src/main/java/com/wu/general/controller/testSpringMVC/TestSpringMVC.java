package com.wu.general.controller.testSpringMVC;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Wu on 2017/8/2.
 */
@Controller
public class TestSpringMVC {

	@RequestMapping({ "/testVelocitiy.htm" })
	public String testController(Model model) {
		model.addAttribute("test", "testVelocitiy");
		return "testVelocitiy";
	}
}
