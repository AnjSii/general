/*
 * Copyright 2017 Esri.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.wu.arcgis.gui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.esri.arcgisruntime.loadable.LoadStatus;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.esri.arcgisruntime.portal.Portal;
import com.esri.arcgisruntime.portal.PortalItem;
import com.esri.arcgisruntime.security.UserCredential;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class OAuthController {

	@FXML private TextField userName;
	@FXML private TextField password;

	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void authenticate(ActionEvent actionEvent) throws Exception {
		if (!userName.getText().equals("") && !password.getText().equals("")) {
			UserCredential credential = new UserCredential(userName.getText(), password.getText());
			Portal portal = new Portal("https://test-hicailiao.maps.arcgis.com", true);
			portal.setCredential(credential);

			portal.addDoneLoadingListener(() -> {
			  if (portal.getLoadStatus() == LoadStatus.LOADED) {
				StackPane stackPane = new StackPane();
				Scene mapScene = new Scene(stackPane);
				stage.setScene(mapScene);
				stage.setTitle("OAuth Sample");
				stage.setWidth(800);
				stage.setHeight(700);
				stage.show();
				getMap(portal, stackPane);
			  } else if (portal.getLoadStatus() == LoadStatus.FAILED_TO_LOAD) {
				// show alert message on error
				showMessage("Authentication failed", portal.getLoadError().getMessage(), Alert.AlertType.ERROR);
			  }
			});

			// loading the portal info of a secured resource
			// this will invoke the authentication challenge
			portal.loadAsync();
		} else {
			showMessage("Authentication failed", "登录失败重新登录", Alert.AlertType.ERROR);
		}
	}

	private void showMessage(String title, String description, Alert.AlertType type) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setContentText(description);
		alert.show();
	}

	private void getMap (Portal portal, StackPane stackPane) {
		// create a map view
		MapView mapView = new MapView();

		String[] portalItemIDs = new String[] {
				"88ddcc7e8d874eb3ae435bf76d176275", "3dd4912b104444118cf44c4241281bf3"
		};

		// create a combo box to list maps
		ComboBox<ArcGISMap> webMapComboBox = new ComboBox<>();

		List<ArcGISMap> webMaps = Stream.of(portalItemIDs)
				.map(id -> new PortalItem(portal, id))
				.map(ArcGISMap::new)
				.collect(Collectors.toList());

		// load maps and add to combo box
		webMaps.forEach(map -> {
			map.getItem().loadAsync();
			map.getItem().addDoneLoadingListener(() -> webMapComboBox.getItems().add(map));
		});

		// listener to switch the map when the selected map changes
		webMapComboBox.getSelectionModel().selectedItemProperty().addListener(e -> {
			ArcGISMap webMap = webMapComboBox.getSelectionModel().getSelectedItem();
			mapView.setMap(webMap);
		});

		// show the name of the map in the combo box
		webMapComboBox.setConverter(new StringConverter<ArcGISMap>() {

		  @Override
		  public String toString(ArcGISMap map) {
			return map != null ? map.getItem().getTitle() : "";
		  }

		  @Override
		  public ArcGISMap fromString(String string) {
			return null; //not needed
		  }
		});

		webMapComboBox.setCellFactory(comboBox -> new ListCell<ArcGISMap>() {

		  @Override
		  protected void updateItem(ArcGISMap map, boolean empty) {
			super.updateItem(map, empty);
			setText(empty ? "" : map.getItem().getTitle());
		  }
		});

		// select the web map loaded first
		webMaps.get(0).getItem().addDoneLoadingListener(() -> webMapComboBox.getSelectionModel().select(0));

		// add the map view and flow panel to stack pane
		stackPane.getChildren().addAll(mapView, webMapComboBox);
		StackPane.setAlignment(webMapComboBox, Pos.TOP_LEFT);
		StackPane.setMargin(webMapComboBox, new Insets(10, 0, 0, 10));
	}
}
