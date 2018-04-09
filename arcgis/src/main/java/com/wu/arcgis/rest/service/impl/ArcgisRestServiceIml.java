package com.wu.arcgis.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wu.arcgis.rest.entity.ArcgisVO;
import com.wu.arcgis.rest.entity.CoordinatesVO;
import com.wu.arcgis.rest.entity.FeaturesVO;
import com.wu.arcgis.rest.entity.GeometryVO;
import com.wu.arcgis.rest.entity.PropertiesVO;
import com.wu.arcgis.rest.service.ArcgisRestService;
import com.wu.general.foundation.domain.Arcgis;
import com.wu.general.foundation.domain.Coordinates;
import com.wu.general.foundation.domain.Features;
import com.wu.general.foundation.service.ArcgisService;

@Service
public class ArcgisRestServiceIml implements ArcgisRestService {

	@Autowired
	private ArcgisService arcgisService;

	@Override
	public ArcgisVO getArcgis() {
		ArcgisVO arcgisVO = new ArcgisVO();
		Arcgis arcgis = this.arcgisService.getObjById(1l);
		if (arcgis != null) {
			arcgisVO.setType(arcgis.getType());
			List<FeaturesVO> featuresVOList = new ArrayList<>();
			for (Features features : arcgis.getFeatures()) {
				FeaturesVO featuresVO = new FeaturesVO();
				GeometryVO geometryVO = new GeometryVO();
				geometryVO.setType(features.getGeometry().getType());
				List<CoordinatesVO> coordinatesVOList = new ArrayList<>();
				for (Coordinates coordinates : features.getGeometry().getCoordinates()) {
					CoordinatesVO coordinatesVO = new CoordinatesVO();
					coordinatesVO.setCoordinates(coordinates.getCoordinates());
					coordinatesVOList.add(coordinatesVO);
				}
				geometryVO.setCoordinates(coordinatesVOList);
				featuresVO.setGeometry(geometryVO);
				PropertiesVO propertiesVO = new PropertiesVO();
				propertiesVO.setName(features.getProperties().getName());
				propertiesVO.setWaterLevel(features.getProperties().getWaterLevel());
				featuresVO.setProperties(propertiesVO);
				featuresVOList.add(featuresVO);
			}
			arcgisVO.setFeatures(featuresVOList);
			return arcgisVO;
		}
		return null;
	}
}
