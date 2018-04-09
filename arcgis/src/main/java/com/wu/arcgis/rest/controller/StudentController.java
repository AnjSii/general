package com.wu.arcgis.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wu.arcgis.rest.entity.StudentVO;
import com.wu.arcgis.rest.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudent() {
			StudentVO student = this.studentService.getStudent();
			return new ResponseEntity<Object>(student, HttpStatus.OK);
	}
}
