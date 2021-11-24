package com.project.photomosaic.image.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.project.photomosaic.image.model.cse.CustomSearch;

public class GsonTest {
	public static final File EXAMPLE_JSON = new File(System.getProperty("user.dir") + "/json/example.json");

	@Test
	public void simpleJSONTest() throws IOException {
		Gson gson = new Gson();
		BufferedReader br = new BufferedReader(new FileReader(EXAMPLE_JSON));
		JsonObject jsonObject = new Gson().fromJson( br.lines().collect(Collectors.joining()) , JsonObject.class);
		for(JsonElement e :jsonObject.getAsJsonArray("items")) {
			System.out.println(e.getAsJsonObject().get("link").toString());
		}
		
	}
}
