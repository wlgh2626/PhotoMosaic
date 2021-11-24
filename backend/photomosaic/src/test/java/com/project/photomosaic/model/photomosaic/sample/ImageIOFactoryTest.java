package com.project.photomosaic.model.photomosaic.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

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
		System.out.println(Runtime.getRuntime().availableProcessors());
		ImageIOFactory factory = new ImageIOFactory(testFiles);
		ArrayList<BufferedImage> images = factory.getImages();
	}
}
