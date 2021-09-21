package com.project.photomosaic;

import org.junit.Test;

import com.project.photomosaic.image.Photomosaic;
import com.project.photomosaic.image.sample.Sample;
import com.project.photomosaic.image.sample.SampleIO;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SampleTest extends Application{

	@Test
	public void autumnSampleTest() throws Exception {
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Sample sample = new Sample(SampleIO.SAMPLE_DEFAULT_PATH + "/test/autumn.tif");
		System.out.println(sample.toString());
		
		ImageDisplay originalDisplay = new ImageDisplay(sample.getOriginal());
		originalDisplay.getStage().show();
		
		ImageDisplay downDisplay = new ImageDisplay(sample.getDownSampled());
		downDisplay.getStage().show();
	}
}
