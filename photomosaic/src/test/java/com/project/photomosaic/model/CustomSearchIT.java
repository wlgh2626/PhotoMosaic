package com.project.photomosaic.model;
import static org.assertj.core.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.photomosaic.TestConfig;
import com.project.photomosaic.image.model.CustomSearch;
import com.project.photomosaic.image.model.sample.SampleIO;
import com.project.photomosaic.util.ImageDisplay;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {TestConfig.class} )
public class CustomSearchIT {
	
	@Autowired
	@Qualifier("testSearch")
	private CustomSearch custom;
	
	@Autowired
	@Qualifier("onionLinks")
	private ArrayList<String> onionLinks;
	
	@Test
	public void searchOnion() {	
		ArrayList<String> customLinks = custom.search("onion");
		
		
		for(String link : onionLinks) {
			System.out.println(link);
		}
		System.out.println("-----------");
		for(String link : customLinks) {
			System.out.println(link);
		};
		
		assertThat(customLinks).containsAnyElementsOf(onionLinks);
	}
	
	
}
