package com.project.photomosaic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.photomosaic.image.Config;
import com.project.photomosaic.image.model.CustomSearch;
import com.project.photomosaic.image.model.Photomosaic;
import com.project.photomosaic.image.model.sample.SampleIO;

@Configuration
public class TestConfig {
	private Logger logger = Logger.getLogger(Config.class.getName());
	
	@Bean(name = "testPhotomosaic")
	public Photomosaic getTestPhotomosaic() throws Exception {
		File LENA = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/lena.tif");
		File SAMPLE = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test");
		return new Photomosaic(LENA , SAMPLE);
	}
	
	@Bean(name = "testSearch")
	public CustomSearch testSearchEngine(){
		String csid = "" , apiKey = "";
		
		try (BufferedReader reader = new BufferedReader(new FileReader(CustomSearch.AUTH_PATH.getPath())) ){
			csid = reader.readLine();
			apiKey = reader.readLine();
		} catch (IOException e) {
			logger.warning("Could not open the file. search ID and api key may be blank");
		} 
		
		return new CustomSearch(csid , apiKey);
	}
	
	@Bean(name = "testImageSearch")
	public CustomSearch testImageSearch() {
		return null;
	}
	
	
	@Bean(name = "onionLinks")
	public ArrayList<String> sampleOnionLinks() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/json/example.json"));
		ArrayList<String> links = CustomSearch.extractLinks(br);
		return links;
	}
	
}
