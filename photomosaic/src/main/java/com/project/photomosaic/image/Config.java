package com.project.photomosaic.image;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.project.photomosaic.image.model.CustomSearch;
import com.project.photomosaic.image.model.Photomosaic;
import com.project.photomosaic.image.model.sample.SampleIO;

@Configuration
@ComponentScan
public class Config {
	private Logger logger = Logger.getLogger(Config.class.getName());
	
	@Bean(name = "testPhotomosaic")
	public Photomosaic getTestPhotomosaic() throws Exception {
		File LENA = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/lena.tif");
		File SAMPLE = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test");
		return new Photomosaic(LENA , SAMPLE);
	}
	
	@Bean(name = "testSearchEngine")
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
}
