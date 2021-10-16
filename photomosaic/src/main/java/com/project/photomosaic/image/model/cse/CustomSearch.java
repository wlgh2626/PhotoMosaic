package com.project.photomosaic.image.model.cse;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

// Example of custom search engine
// https://www.googleapis.com/customsearch/v1?key=INSERT_YOUR_API_KEY&cx=017576662512468239146:omuauf_lfve&q=lectures

public class CustomSearch {
	public static final File AUTH_PATH = new File(System.getProperty("user.dir") + "/apikey/auth.txt");
	private static final Logger logger = Logger.getLogger(CustomSearch.class.getName());
	public final String BASE_URL = "https://www.googleapis.com/customsearch/v1?";
	
	private String apiKey, customSearchID, searchType;
	
	public CustomSearch(String customSearchID , String apiKey) {
		this.apiKey = apiKey;
		this.customSearchID = customSearchID;
		searchType = "searchType=image";
	}
	
	/*
	 * Connects to Google's Custom Search Engine and retrieves image link
	 */	
	public ArrayList<String> search(String searchQuery){
		ArrayList<String> links = new ArrayList<String>();
		String search = "q=" + String.join( "+" , searchQuery.split(" "));
		String query = String.join( "&" , apiKey, customSearchID , searchType , search);
		
		HttpURLConnection connection = null;
		try {
			URL url = new URL(BASE_URL + query);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "application/json");
		    BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
		    links = extractLinks(br);
		 
		} catch (MalformedURLException e) {
			logger.warning(BASE_URL + query + " is malformed URL. Thus could not be parsed properly");
		} catch (ProtocolException e) {
			logger.warning(e.toString());
		} catch (IOException e) {
			logger.warning(e.toString());
		} finally {
			connection.disconnect();
		}
		return links;
	}
	
	public ArrayList<BufferedImage> searchImage(ArrayList<String> links) throws IOException {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		
		for(String link : links) {
			try {
				link = link.replace("\"", "");	//remove double quotes from url link
				URL url = new URL(link);
				images.add(ImageIO.read(url));
			} catch (MalformedURLException e) {
				logger.warning(e.toString());
			}
		}
		
		return images; 
	}
	
	public ArrayList<BufferedImage> searchImage(String searchQuery) throws IOException {
		return searchImage( search(searchQuery) );
	}
	
	public static ArrayList<String> extractLinks(BufferedReader br) throws IOException {
		ArrayList<String> links = new ArrayList<String>();
		JsonObject jsonObject = new Gson().fromJson( br.lines().collect(Collectors.joining()) , JsonObject.class);
			
		for(JsonElement e :jsonObject.getAsJsonArray("items")) {
			links.add(e.getAsJsonObject().get("link").toString());
		}
		return links;
	}
}
