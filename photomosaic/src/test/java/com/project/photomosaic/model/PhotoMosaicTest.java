package com.project.photomosaic.model;

import java.io.File;

import org.junit.Test;

import com.project.photomosaic.image.model.Photomosaic;
import com.project.photomosaic.image.model.sample.SampleIO;
import com.project.photomosaic.util.ImageDisplay;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PhotoMosaicTest extends Application{
	private File sample = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test");
	

	@Test
	public void photoMosaicTest() throws Exception {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		long start = System.currentTimeMillis();
		Photomosaic mosaic = new Photomosaic( ImageDisplay.DUCK , sample);
		mosaic.build();
		long end = System.currentTimeMillis();
		System.out.println("Time to construct PhotoMosaic: " + (end - start)/1000.0 + " seconds");
		ImageDisplay display = new ImageDisplay(mosaic.getImage());

		HBox hbox = new HBox(display.getImageView());
		Scene scene = new Scene(hbox);

		primaryStage.setTitle("ImageView");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
