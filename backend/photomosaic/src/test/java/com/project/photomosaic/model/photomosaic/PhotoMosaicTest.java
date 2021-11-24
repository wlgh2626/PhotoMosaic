package com.project.photomosaic.model.photomosaic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.model.photomosaic.PhotomosaicTestConfig;
import com.project.photomosaic.image.model.photomosaic.Photomosaic;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { PhotomosaicTestConfig.class })
public class PhotoMosaicTest {

	@Autowired
	@Qualifier("duckImage")
	private BufferedImage duck;

	@Autowired
	@Qualifier("sampleImages")
	private ArrayList<BufferedImage> samples;

	@Test
	public void photoMosaicTest() throws Exception {
		long start = System.currentTimeMillis();
		Photomosaic mosaic = new Photomosaic(duck, samples);
		long end = System.currentTimeMillis();
		System.out.println("Time to construct PhotoMosaic: " + (end - start) / 1000.0 + " seconds");
	}

}
