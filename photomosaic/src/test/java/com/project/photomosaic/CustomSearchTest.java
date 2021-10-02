package com.project.photomosaic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.project.photomosaic.image.model.CustomSearch;

public class CustomSearchTest {
	@Test
	public void simpleSearchTest() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/apikey/auth.txt"));
		String csid = reader.readLine();
		String apiKey = reader.readLine();
		CustomSearch search = new CustomSearch(csid , apiKey);
		search.search("onions");
	}
}
