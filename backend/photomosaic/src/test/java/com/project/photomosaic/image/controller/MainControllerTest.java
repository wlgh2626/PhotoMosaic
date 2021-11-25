package com.project.photomosaic.image.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.image.controller.MainController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = MainControllerTestConfig.class)
public class MainControllerTest {
	@Autowired
	MainController main;

	@Test
	public void pingTest() {
		Assert.assertTrue("pong" == main.ping());
	}

	@Test
	public void photomosaicTest() throws Exception {
		String url = main.request("kurmd3fx");
		Assert.assertTrue(url.equals("https://js-image-storage.s3.us-east-2.amazonaws.com/kurmd3fx/photomosaic.png"));
	}

	@Test
	public void largePhotomosaicTest() throws Exception {
		String url = main.request("testBig");
		Assert.assertTrue(url.equals("https://js-image-storage.s3.us-east-2.amazonaws.com/testBig/photomosaic.png"));
	}

}
