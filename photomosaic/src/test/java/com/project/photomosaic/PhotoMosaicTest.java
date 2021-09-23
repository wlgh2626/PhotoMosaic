package com.project.photomosaic;

import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.project.photomosaic.image.Photomosaic;
import com.project.photomosaic.image.dither.DitheredImage;
import com.project.photomosaic.image.sample.SampleIO;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PhotoMosaicTest extends Application{
	private File sample = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test");
	private File original = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/" + ImageDisplay.ORIGINAL_TEST_FILES.get(0));

	@Test
	public void DitheringTest() throws Exception {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Photomosaic mosaic = new Photomosaic(original , sample);
		ImageDisplay display = new ImageDisplay(mosaic.getImage());

		HBox hbox = new HBox(display.getImageView());
		Scene scene = new Scene(hbox);

		primaryStage.setTitle("ImageView");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}