package com.project.photomosaic.image.model.photomosaic.sample;

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

import com.project.photomosaic.image.TestApplication;
import com.project.photomosaic.image.model.photomosaic.PhotomosaicTestConfig;
import com.project.photomosaic.image.model.photomosaic.sample.SampleContainer;
import com.project.photomosaic.image.model.utils.ImageIOThreads;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
@ContextConfiguration(classes = PhotomosaicTestConfig.class)
public class SampleContainerTest {
	@Autowired
	@Qualifier("testFiles")
	ArrayList<File> testFiles;

	@Autowired
	@Qualifier("testFileBytesList")
	ArrayList<byte[]> fileBytesList;

	@Autowired
	@Qualifier("singleCore")
	ImageIOThreads singleFactory;

	@Autowired
	@Qualifier("multiCore")
	ImageIOThreads multiFactory;

	@Test
	public void simpleContainerTest() throws Exception {
		long startSingle = System.currentTimeMillis();
		SampleContainer sampleImages1 = new SampleContainer(singleFactory.asBufferedImages(fileBytesList));
		long endSingle = System.currentTimeMillis();
		long totalSingle = endSingle - startSingle;
		System.out.println("Time to load Samples: " + (endSingle - startSingle) / 1000.0 + " seconds");

		long startMulti = System.currentTimeMillis();
		SampleContainer sampleImages2 = new SampleContainer(multiFactory.asBufferedImages(fileBytesList));
		long endMulti = System.currentTimeMillis();
		long totalMulti = endMulti - startMulti;
		System.out.println("Time to load Samples: " + (endMulti - startMulti) / 1000.0 + " seconds");
		Assert.assertTrue(totalMulti < totalSingle);
	}
}
