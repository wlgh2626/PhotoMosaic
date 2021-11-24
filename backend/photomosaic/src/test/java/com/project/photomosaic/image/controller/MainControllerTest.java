package com.project.photomosaic.image.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.image.Config;
import com.project.photomosaic.image.controller.MainController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { Config.class })
public class MainControllerTest {
	MainController main = new MainController();

	@Test
	public void pingTest() {
		Assert.assertTrue("pong" == main.ping());
	}

	@Test
	public void photomosaicTest() throws Exception {
		String url = main.request("kurmd3fx");
		Assert.assertTrue(url == "https://js-image-storage.s3.us-east-2.amazonaws.com/kurmd3fx/photomosaic.png");
	}

}
