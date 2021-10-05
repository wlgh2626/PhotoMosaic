package com.project.photomosaic.model.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import com.project.photomosaic.image.model.sample.Sample;
import com.project.photomosaic.image.model.sample.SampleIO;
import com.project.photomosaic.util.ImageDisplay;

import javafx.application.Application;
import javafx.stage.Stage;

public class SampleTest extends Application{
	private File file = new File(ImageDisplay.SAMPLE.getPath() + "/autumn.tif");
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
	
	public void samplePerformanceTests() throws Exception {
		//SampleIO sampleio = new SampleIO(SampleIO.SAMPLE_DEFAULT_PATH + "/test");
		
	}
	
}
