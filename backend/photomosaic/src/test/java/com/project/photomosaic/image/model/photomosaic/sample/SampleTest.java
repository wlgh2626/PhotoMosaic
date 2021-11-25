package com.project.photomosaic.image.model.photomosaic.sample;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.image.TestApplication;
import com.project.photomosaic.image.model.photomosaic.PhotomosaicTestConfig;
import com.project.photomosaic.image.model.photomosaic.sample.Sample;
import com.project.photomosaic.image.util.ImageDisplay;

import javafx.application.Application;
import javafx.stage.Stage;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ContextConfiguration(classes = PhotomosaicTestConfig.class)
public class SampleTest extends Application {
	private File file = new File(PhotomosaicTestConfig.DUCK.getPath());

	@Test
	public void SampleTests() throws Exception {
		simpleSampleTest();
	}

	public void simpleSampleTest() throws Exception {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		BufferedImage image = ImageIO.read(file);
		long start = System.currentTimeMillis();
		Sample sample = new Sample(image);
		long end = System.currentTimeMillis();
		System.out.println("DEBUG: Loading" + file.toPath() + "took " + (end - start) + " MilliSeconds");
		System.out.println(sample.toString());

		ImageDisplay originalDisplay = new ImageDisplay(sample.getOriginal());
		originalDisplay.getStage().show();

		ImageDisplay downDisplay = new ImageDisplay(sample.getDownSampled());
		downDisplay.getStage().show();

		sample.setDimension(80);
		ImageDisplay modifiedDisplay = new ImageDisplay(sample.getDownSampled());
		modifiedDisplay.getStage().show();

	}

}
