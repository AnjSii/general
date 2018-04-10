package com.wu.arcgis.rest.service.impl;

import java.math.BigDecimal;
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

				List<BigDecimal> coordinatesList = new ArrayList<>();
				coordinatesList.add(features.getGeometry().getCoordinates().getLongitude());
				coordinatesList.add(features.getGeometry().getCoordinates().getLatitude());
				geometryVO.setCoordinates(coordinatesList);

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
