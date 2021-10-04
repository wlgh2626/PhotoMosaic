package com.project.photomosaic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.photomosaic.image.model.CustomSearch;

public class GsonTest {
	public static final File EXAMPLE_JSON = new File(System.getProperty("user.dir") + "/json/example.json");

	@Test
	public void simpleJSONTest() throws IOException {
		Gson gson = new Gson();
		BufferedReader reader = new BufferedReader(new FileReader(EXAMPLE_JSON));
		
		String json ="";
		String output;
		while( (output = reader.readLine()) != null) {
			json += output + "\n"; 
		}
		
		JsonObject jsonObject = gson.fromJson( json , JsonObject.class);
		//String result = jobj.get("items").getAsString();
		System.out.println(jsonObject);
	}
}
