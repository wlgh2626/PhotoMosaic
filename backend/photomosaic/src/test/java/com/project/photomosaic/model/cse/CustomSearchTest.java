package com.project.photomosaic.model.cse;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.TestConfig;
import com.project.photomosaic.image.model.cse.CustomSearch;
import com.project.photomosaic.image.model.photomosaic.sample.SampleIO;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class} )
public class CustomSearchTest {
	@Autowired
	@Qualifier("testSearch")
	private CustomSearch custom;
	
	@Autowired
	@Qualifier("onionLinks")
	private ArrayList<String> onionLinks;
	
	@Test
	public void retrieveOnionImage() throws IOException {
		ArrayList<BufferedImage> images = custom.searchImage(onionLinks);
		int i = 0;
		for(BufferedImage image : images) {
			String fileName  = SampleIO.SAMPLE_DEFAULT_PATH + "/test/sample" + (i++) + ".jpg";
			ImageIO.write(image, "jpg", new File(fileName));
		}
	}
}
