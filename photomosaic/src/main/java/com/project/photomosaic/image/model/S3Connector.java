package com.project.photomosaic.image.model;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class S3Connector {
	private static final Logger logger = Logger.getLogger(S3Connector.class.getName());
	private static final String AUTH_PATH = System.getProperty("user.dir") + "/apiKey/s3auth.js";
	
	private final String bucketName, accessKey , secretKey;
	private Regions region = Regions.US_EAST_2;
	
	private AmazonS3 s3;
	public S3Connector(String folderName) throws IOException{
		JsonObject json = new Gson().fromJson( new FileReader(AUTH_PATH) , JsonObject.class);
		bucketName = json.get("bucketName").getAsString();
		accessKey = json.get("accessKey").getAsString();
		secretKey = json.get("secretKey").getAsString();
		
		BasicAWSCredentials credential = new BasicAWSCredentials(accessKey, secretKey);
		s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credential))
                .withRegion(region)
                .build();
		ObjectListing s3Objects = s3.listObjects(bucketName, folderName);
		for(S3ObjectSummary summary : s3Objects.getObjectSummaries()) {
			System.out.println(summary.getKey());
		}
		
	}
	
}
