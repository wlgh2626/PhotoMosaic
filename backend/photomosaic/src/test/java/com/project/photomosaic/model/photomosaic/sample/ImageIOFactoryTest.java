package com.project.photomosaic.model.photomosaic.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.TestConfig;
import com.project.photomosaic.image.model.photomosaic.sample.ImageIOFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { TestConfig.class })
public class ImageIOFactoryTest {
	@Autowired
	@Qualifier("testFiles")
	ArrayList<File> testFiles;

	@Test
	public void ParallelImageIOTest() throws InterruptedException {
		System.out.println("There are " + Runtime.getRuntime().availableProcessors() + " available cores");
		ImageIOFactory factory1 = new ImageIOFactory();
		long start1 = System.currentTimeMillis();
		ArrayList<BufferedImage> images1 = factory1.asBufferedImages(testFiles);
		long end1 = System.currentTimeMillis();
		long singleCoreTime = end1 - start1;

		ImageIOFactory factory2 = new ImageIOFactory(Runtime.getRuntime().availableProcessors());
		long start2 = System.currentTimeMillis();
		ArrayList<BufferedImage> images2 = factory2.asBufferedImages(testFiles);
		long end2 = System.currentTimeMillis();
		long multiCoreTime = end2 - start2;

		Assert.assertTrue(multiCoreTime < singleCoreTime);
	}
}
