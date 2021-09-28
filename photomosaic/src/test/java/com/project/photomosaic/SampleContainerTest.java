package com.project.photomosaic;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.project.photomosaic.image.sample.SampleContainer;
import com.project.photomosaic.image.sample.SampleIO;

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

		for (String fileName : sampleImages.getFileNames()) {
			assertTrue(filesExpected.contains(fileName));
		}
	}
}
