package com.project.photomosaic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.photomosaic.image.Config;
import com.project.photomosaic.image.model.cse.CustomSearch;
import com.project.photomosaic.image.model.photomosaic.sample.SampleIO;
import com.project.photomosaic.image.model.s3.S3Connector;

@Configuration
public class TestConfig {
	private Logger logger = Logger.getLogger(Config.class.getName());

	@Bean(name = "testS3Connector")
	public S3Connector getS3Connector() {
		S3Connector s3 = new S3Connector();
		return s3;
	}

	@Bean(name = "testFiles")
	public ArrayList<File> getTestFiles() {
		File targetPath = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test");
		File[] files = targetPath.listFiles();
		return new ArrayList<File>(Arrays.asList(files));
	}

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
