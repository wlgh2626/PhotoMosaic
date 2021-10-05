package com.project.photomosaic.model.dither;


import javax.imageio.ImageIO;

import org.junit.Test;

import com.project.photomosaic.image.model.dither.DitheredImage;
import com.project.photomosaic.util.ImageDisplay;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DitheringTest extends Application {

	@Test
	public void DitheringTest() throws Exception {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		long start = System.currentTimeMillis();
		DitheredImage dithered = new DitheredImage(ImageIO.read(ImageDisplay.LENA));
		long end = System.currentTimeMillis();
		System.out.println("Time to load Samples: " + (end - start)/1000.0 + " seconds");
		System.out.println(dithered.toString());
		ImageDisplay display = new ImageDisplay(dithered.getDitheredImage());

		HBox hbox = new HBox(display.getImageView());
		Scene scene = new Scene(hbox);

		primaryStage.setTitle("ImageView");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
