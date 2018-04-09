package com.wu.arcgis.rest.service.impl;

import org.springframework.stereotype.Service;

import com.wu.arcgis.rest.entity.StudentVO;
import com.wu.arcgis.rest.service.StudentService;

@Service
public class StudentServiceIml implements StudentService {

	public StudentVO getStudent () {
		StudentVO studentVO = new StudentVO();
		studentVO.setStudent_id(Long.valueOf(1));
		studentVO.setStudent_name("Jack");
		return studentVO;
	}
}
