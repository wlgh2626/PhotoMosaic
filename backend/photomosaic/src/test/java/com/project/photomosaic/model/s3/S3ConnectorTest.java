package com.project.photomosaic.model.s3;

import org.junit.Test;

import com.project.photomosaic.image.model.s3.S3Connector;
import com.project.photomosaic.util.ImageDisplay;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class S3ConnectorTest extends Application{

	@Test
	public void imageRetrievalTest() {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		S3Connector s3 = new S3Connector();
		s3.setFolderName("kurmd3fx");
		ImageDisplay display = new ImageDisplay(s3.getOriginalImage());

		HBox hbox = new HBox(display.getImageView());
		Scene scene = new Scene(hbox);

		primaryStage.setTitle("ImageView");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
