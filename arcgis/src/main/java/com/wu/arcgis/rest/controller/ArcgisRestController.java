package com.wu.arcgis.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wu.arcgis.rest.entity.ArcgisVO;
import com.wu.arcgis.rest.service.ArcgisRestService;

@RestController
public class ArcgisRestController {

	@Autowired
	private ArcgisRestService arcgisRestService;

	@RequestMapping(value = "/arcgis.arc", method = RequestMethod.GET)
	public ResponseEntity<Object> getStudent() {
		ArcgisVO arcgisVO = this.arcgisRestService.getArcgis();
		return new ResponseEntity<Object>(arcgisVO, HttpStatus.OK);
	}
}
