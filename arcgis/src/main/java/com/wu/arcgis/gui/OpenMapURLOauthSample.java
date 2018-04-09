package com.wu.arcgis.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenMapURLOauthSample extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/oauth_controller.fxml"));
		Parent root = loader.load();
		OAuthController controller = loader.getController();
		controller.setStage(stage);
		Scene scene = new Scene(root);

		// set title, size, and add scene to stage
		stage.setTitle("OAuth Sample");
		stage.setWidth(400);
		stage.setHeight(400);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
