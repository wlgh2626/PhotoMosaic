package com.project.photomosaic.image.model.photomosaic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.image.TestApplication;
import com.project.photomosaic.image.model.photomosaic.Photomosaic;
import com.project.photomosaic.image.model.photomosaic.PhotomosaicTestConfig;
import com.project.photomosaic.image.model.utils.ImageIOThreads;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ContextConfiguration(classes = PhotomosaicTestConfig.class)
public class PhotomosaicTest {
	@Autowired
	@Qualifier("duckImage")
	private BufferedImage duck;

	@Autowired
	@Qualifier("testFileBytesList")
	private ArrayList<byte[]> fileBytesList;

	@Autowired
	@Qualifier("multiCore")
	private ImageIOThreads factory;

	@Autowired
	@Qualifier("ditherSize")
	int ditherSize;

	@Test
	public void photoMosaicTest() throws Exception {
		ArrayList<BufferedImage> samples = factory.asBufferedImages(fileBytesList);

		long start = System.currentTimeMillis();
		Photomosaic mosaic = new Photomosaic(duck, samples, ditherSize);
		mosaic.build();
		long end = System.currentTimeMillis();
		System.out.println("Time to construct PhotoMosaic: " + (end - start) / 1000.0 + " seconds");
	}

}
