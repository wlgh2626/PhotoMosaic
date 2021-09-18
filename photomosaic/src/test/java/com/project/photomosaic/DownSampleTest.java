package com.project.photomosaic;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.project.photomosaic.image.Photomosaic;
import com.project.photomosaic.image.sample.SampleContainer;
import com.project.photomosaic.image.sample.SampleIO;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DownSampleTest extends Application {
	private File sample = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test");

	@Test
	public void balloonsIFDTest() throws Exception {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		SampleContainer samples = new SampleContainer(sample.toString());
		ImageDisplay display = new ImageDisplay(samples.getSampleList().get(0).getDownSampled());

		HBox hbox = new HBox(display.getImageView());
		Scene scene = new Scene(hbox);

		primaryStage.setTitle("ImageView");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
