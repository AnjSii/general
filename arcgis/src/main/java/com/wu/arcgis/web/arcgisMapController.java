package com.wu.arcgis.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class arcgisMapController {
	@RequestMapping({ "/arcgisMap.htm" })
	public String welcome(Model model) {
		return "FeatureLayer";
	}
}
