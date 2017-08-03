package com.wu.general.controller.testJPAGeneral;

import java.util.Map;

import com.wu.general.foundation.domain.TestJPAGeneral;
import com.wu.general.foundation.service.testJPAGeneralService.TestJPAGeneralService;

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

	@RequestMapping({ "/testJPAGeneralfindById.htm" })
	public String testService(Long id, Map<String, Object> map) {
		TestJPAGeneral testJPA = testJPAGeneralService.findById(id);
		map.put("id", testJPA.getId());
		map.put("name", testJPA.getName());
		return "testJPAGeneralfindById";
	}
}
