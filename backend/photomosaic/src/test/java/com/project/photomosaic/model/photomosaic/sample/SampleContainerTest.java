package com.project.photomosaic.model.photomosaic.sample;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.project.photomosaic.image.model.photomosaic.sample.SampleContainer;
import com.project.photomosaic.image.model.photomosaic.sample.SampleIO;
import com.project.photomosaic.util.ImageDisplay;

public class SampleContainerTest {
	public static ArrayList<String> filesExpected = new ArrayList<String>() {
		{
			add("autumn.tif");
			add("balloons.tif");
			add("board.tif");
			add("brain.tif");
			add("columns.tif");
			add("lena.tif");
		}
	};

	@Test
	public void simpleContainerTest() throws Exception {
		long start = System.currentTimeMillis();
		SampleContainer sampleImages = new SampleContainer(ImageDisplay.SAMPLE.getPath());
		long end = System.currentTimeMillis();
		System.out.println("Time to load Samples: " + (end - start)/1000.0 + " seconds");
		System.out.println(sampleImages.toString());

	}
}
