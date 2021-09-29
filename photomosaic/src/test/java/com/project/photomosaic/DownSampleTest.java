package com.project.photomosaic;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.project.photomosaic.image.model.Photomosaic;
import com.project.photomosaic.image.model.sample.SampleContainer;
import com.project.photomosaic.image.model.sample.SampleIO;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DownSampleTest extends Application {

	@Test
	public void balloonsIFDTest() throws Exception {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		SampleContainer samples = new SampleContainer(ImageDisplay.SAMPLE.getPath());
		ImageDisplay display = new ImageDisplay(samples.getSampleList().get(0).getDownSampled());

		HBox hbox = new HBox(display.getImageView());
		Scene scene = new Scene(hbox);

		primaryStage.setTitle("ImageView");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
