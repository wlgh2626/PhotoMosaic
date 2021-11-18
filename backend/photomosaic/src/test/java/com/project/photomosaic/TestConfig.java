package com.project.photomosaic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.photomosaic.image.model.Config;
import com.project.photomosaic.image.model.cse.CustomSearch;

@Configuration
public class TestConfig {
	private Logger logger = Logger.getLogger(Config.class.getName());

	@Bean(name = "testSearch")
	public CustomSearch getTestSearchEngine() {
		String csid = "";
		String apiKey = "";

		try {
			JsonObject json = new Gson().fromJson(new FileReader(CustomSearch.AUTH_PATH), JsonObject.class);
			csid = json.get("cx").getAsString();
			apiKey = json.get("key").getAsString();
		} catch (IOException e) {
			logger.warning("Could not open the file. search ID and api key may be blank");
		}

		return new CustomSearch(csid, apiKey);
	}

	@Bean(name = "testImageSearch")
	public CustomSearch getTestImageSearch() {
		return null;
	}

	@Bean(name = "onionLinks")
	public ArrayList<String> getSampleOnionLinks() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/json/example.json"));
		ArrayList<String> links = CustomSearch.extractLinks(br);
		return links;
	}

}
