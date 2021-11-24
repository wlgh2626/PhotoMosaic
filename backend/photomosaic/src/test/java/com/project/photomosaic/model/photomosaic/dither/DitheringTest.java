package com.project.photomosaic.model.photomosaic.dither;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.image.model.photomosaic.dither.DitheredImage;
import com.project.photomosaic.model.photomosaic.PhotomosaicTestConfig;
import com.project.photomosaic.util.ImageDisplay;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { PhotomosaicTestConfig.class })
public class DitheringTest extends Application {

	@Test
	public void DitheringTest() throws Exception {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		long start = System.currentTimeMillis();
		DitheredImage dithered = new DitheredImage(ImageIO.read(PhotomosaicTestConfig.LENA));
		long end = System.currentTimeMillis();
		System.out.println("Time to load Samples: " + (end - start) / 1000.0 + " seconds");
		System.out.println(dithered.toString());
		ImageDisplay display = new ImageDisplay(dithered.getDitheredImage());

		HBox hbox = new HBox(display.getImageView());
		Scene scene = new Scene(hbox);

		primaryStage.setTitle("ImageView");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
