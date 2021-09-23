package com.project.photomosaic;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.project.photomosaic.image.sample.Sample;
import com.project.photomosaic.image.sample.SampleIO;

import javafx.application.Application;
import javafx.stage.Stage;

public class SampleTest extends Application{

	@Test
	public void autumnSampleTest() throws Exception {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		File file = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test/autumn.tif");
		BufferedImage image = ImageIO.read(file);
		Sample sample = new Sample(image);
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
