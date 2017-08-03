package com.wu.general.controller.testJPA;

import java.util.Map;

import com.wu.general.foundation.service.testJPAService.TestJPAGeneralService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Wu on 2017/8/3.
 */
@Controller
public class TestJPAGeneralController {

	@Autowired
	private TestJPAGeneralService testJPAGeneralService;

	@RequestMapping({ "/testJPAfindById.htm" })
	public String testService(Long id, Map<String, Object> map) {
		com.wu.general.foundation.domain.TestJPAGeneral testJPA = testJPAGeneralService.findById(id);
		map.put("id", testJPA.getId());
		map.put("name", testJPA.getName());
		return "testJPAfindById";
	}
}
