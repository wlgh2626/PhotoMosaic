package com.project.photomosaic;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import com.project.photomosaic.image.Photomosaic;
import com.project.photomosaic.image.sample.SampleContainer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DitheringTest extends Application {
	public static final ArrayList<String> files = new ArrayList<String>() {
		{
			add("duck.jpg");
		}
	};
	private File sample = new File(SampleContainer.SAMPLE_DEFAULT_PATH + "/test");
	private File original = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/" + files.get(0));

	@Test
	public void balloonsIFDTest() throws Exception {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Photomosaic dithered = new Photomosaic(original, sample);
		ImageDisplay display = new ImageDisplay(dithered.getOriginalImage());

		HBox hbox = new HBox(display.getImageView());
		Scene scene = new Scene(hbox);

		primaryStage.setTitle("ImageView");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
