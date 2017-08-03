package com.wu.general.controller.testSpring;

import java.util.List;
import java.util.Map;

import com.wu.general.foundation.service.testService.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Wu on 2017/8/3.
 */
@Controller
public class TestSpring {

	@Autowired
	private TestService testService;

	@RequestMapping({ "/testSpring.htm" })
	public String testService(Map<String, List<String>> map) {
		map.put("testSpring", testService.test());
		return "testSpring";
	}
}
