package com.project.photomosaic.image.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

// Example of custom search engine
// https://www.googleapis.com/customsearch/v1?key=INSERT_YOUR_API_KEY&cx=017576662512468239146:omuauf_lfve&q=lectures

public class CustomSearch {
	public static final File AUTH_PATH = new File(System.getProperty("user.dir") + "/apikey/auth.txt");
	
	private Logger logger = Logger.getLogger(CustomSearch.class.getName());
	public final String BASE_URL = "https://www.googleapis.com/customsearch/v1?";
	
	private String apiKey, customSearchID, searchType;
	
	public CustomSearch(String customSearchID , String apiKey) {
		this.apiKey = apiKey;
		this.customSearchID = customSearchID;
		searchType = "searchType=image";
	}
	
	public String search(String searchQuery){
		String jsonResult = "";
		String search = "q=" + String.join( "+" , searchQuery.split(" "));
		String query = String.join( "&" , apiKey, customSearchID , searchType , search);
		
		HttpURLConnection connection = null;
		try {
			URL url = new URL(BASE_URL + query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
		    BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		 
			String output;
			while( (output = br.readLine()) != null ) {
				jsonResult += output + "\n";
			}
			
		} catch (MalformedURLException e) {
			logger.warning(BASE_URL + query + " is malformed URL. Thus could not be parsed properly");
		} catch (ProtocolException e) {
			logger.warning(e.toString());
		} catch (IOException e) {
			logger.warning(e.toString());
		} finally {
			connection.disconnect();
		}
		return jsonResult;
	}
	
}
