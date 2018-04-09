package com.wu.arcgis.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wu.general.foundation.domain.Arcgis;
import com.wu.general.foundation.domain.Features;
import com.wu.general.foundation.service.ArcgisService;

@Controller
public class arcgisMapController {

	@Autowired
	private ArcgisService arcgisService;

	@RequestMapping({ "/arcgisMap.htm" })
	public String welcome(Model model) {
		Arcgis arcgis = this.arcgisService.getObjById(1L);
		List<Features> features = arcgis.getFeatures();
		return "arcgisMap";
	}
}
