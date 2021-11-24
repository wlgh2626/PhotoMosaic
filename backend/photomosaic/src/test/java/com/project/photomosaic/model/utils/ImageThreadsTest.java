package com.project.photomosaic.model.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.image.model.utils.ImageThreads;
import com.project.photomosaic.model.photomosaic.PhotomosaicTestConfig;

/**
 * Note, the test may fail, if there are not enough sample images and make
 * Thread overhead to slow down the parallel process.
 * 
 * @author Jiho
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { PhotomosaicTestConfig.class })
public class ImageThreadsTest {
	@Autowired
	@Qualifier("testFiles")
	ArrayList<File> testFiles;

	@Test
	public void ParallelImageIOTest() throws InterruptedException, IOException {
		ArrayList<byte[]> fileBytesList = new ArrayList<byte[]>();
		for (File file : testFiles) {
			fileBytesList.add(FileUtils.readFileToByteArray(file));
		}
		int numThreads = Runtime.getRuntime().availableProcessors();
		System.out.println("There are " + numThreads + " available cores");

		ImageThreads factory1 = new ImageThreads(1); // 1 core only
		long start1 = System.currentTimeMillis();
		ArrayList<BufferedImage> images1 = factory1.asBufferedImages(fileBytesList);
		long end1 = System.currentTimeMillis();
		long singleCoreTime = end1 - start1;
		System.out.println("Time for single core: " + singleCoreTime / 1000 + "seconds");

		ImageThreads factory2 = new ImageThreads(numThreads); // all cores
		long start2 = System.currentTimeMillis();
		ArrayList<BufferedImage> images2 = factory2.asBufferedImages(fileBytesList);
		long end2 = System.currentTimeMillis();
		long multiCoreTime = end2 - start2;
		System.out.println("Time for " + numThreads + " cores: " + multiCoreTime / 1000 + "seconds");

		Assert.assertTrue(multiCoreTime < singleCoreTime);
	}
}
