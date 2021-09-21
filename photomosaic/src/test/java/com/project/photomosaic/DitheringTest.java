package com.project.photomosaic;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.project.photomosaic.image.Photomosaic;
import com.project.photomosaic.image.sample.SampleIO;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DitheringTest extends Application {
	private File sample = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test");
	private File original = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/" + ImageDisplay.ORIGINAL_TEST_FILES.get(0));

	@Test
	public void balloonsIFDTest() throws Exception {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Photomosaic photomosaic = new Photomosaic(original, sample);
		ImageDisplay display = new ImageDisplay(photomosaic.getImage());

		HBox hbox = new HBox(display.getImageView());
		Scene scene = new Scene(hbox);

		primaryStage.setTitle("ImageView");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
