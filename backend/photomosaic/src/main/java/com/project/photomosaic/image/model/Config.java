package com.project.photomosaic.image.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.photomosaic.image.model.cse.CustomSearch;
import com.project.photomosaic.image.model.photomosaic.sample.ImageIOFactory;

@Configuration
@ComponentScan
public class Config {
	private Logger logger = Logger.getLogger(Config.class.getName());

	/*
	 * @Bean(name = "photomosaic") public Photomosaic photomosaic() throws Exception
	 * { File LENA = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/lena.tif"); File
	 * SAMPLE = new File(SampleIO.SAMPLE_DEFAULT_PATH); return new Photomosaic(LENA,
	 * SAMPLE); }
	 */

	@Bean(name = "search")
	public CustomSearch searchEngine() {
		String csid = "", apiKey = "";
		try (BufferedReader reader = new BufferedReader(new FileReader(CustomSearch.AUTH_PATH.getPath()))) {
			JsonObject json = new Gson().fromJson(new FileReader(CustomSearch.AUTH_PATH), JsonObject.class);
			csid = json.get("cx").getAsString();
			apiKey = json.get("key").getAsString();
		} catch (IOException e) {
			logger.warning("Could not open the file. search ID and api key may be blank");
		}
		return new CustomSearch(csid, apiKey);
	}

	@Bean(name = "ImageIOFactory")
	public ImageIOFactory getImageIOFactory() {
		return new ImageIOFactory(Runtime.getRuntime().availableProcessors());
	}

}
