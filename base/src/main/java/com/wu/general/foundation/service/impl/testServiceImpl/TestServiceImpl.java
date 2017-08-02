package com.wu.general.foundation.service.impl.testServiceImpl;

import java.util.ArrayList;
import java.util.List;

import com.wu.general.foundation.service.testService.TestService;

/**
 * Created by Wu on 2017/8/2.
 */
public class TestServiceImpl implements TestService {
	@Override
	public List<String> test() {
		List<String> test = new ArrayList<>();
		test.add("Tset");
		test.add("Spring");
		return test;
	}
}
