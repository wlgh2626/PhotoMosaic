package com.project.photomosaic.image.model;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.*;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


public class S3Connector {
	private static final Logger logger = Logger.getLogger(S3Connector.class.getName());
	
	private String bucketName = "js-image-storage";
	private String accessKey , secretKey;
	private Regions region = Regions.US_EAST_2;
	
	public S3Connector(String folderName) throws IOException{
		retrieve();
		BasicAWSCredentials credential = new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credential))
                .withRegion(region)
                .build();
		ObjectListing s3Objects = s3.listObjects(bucketName, folderName);
		for(S3ObjectSummary summary : s3Objects.getObjectSummaries()) {
			System.out.println(summary.getKey());
		}
	}
	
	public void retrieve() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("apiKey/s3auth.txt"));
		accessKey = reader.readLine();
		secretKey = reader.readLine();
	}
	
}
