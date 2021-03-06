package com.project.photomosaic.image.model.s3;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;

import org.apache.commons.io.IOUtils;
import org.springframework.test.context.ContextConfiguration;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.photomosaic.image.config.Config;
import com.project.photomosaic.image.model.utils.ImageIOThreads;

@ContextConfiguration(classes = { Config.class })
public class S3Connector {
	private static final Logger logger = Logger.getLogger(S3Connector.class.getName());
	private static final String AUTH_PATH = System.getProperty("user.dir") + "/apikey/s3auth.js";

	private ImageIOThreads imageIOFactory = new ImageIOThreads(Runtime.getRuntime().availableProcessors());

	private String bucketName, accessKey, secretKey, folderName;
	private Regions region = Regions.US_EAST_2;

	private AmazonS3 s3;

	public S3Connector() {
		folderName = "default";
		try {
			JsonObject json = new Gson().fromJson(new FileReader(AUTH_PATH), JsonObject.class);
			bucketName = json.get("bucketName").getAsString();
			accessKey = json.get("accessKey").getAsString();
			secretKey = json.get("secretKey").getAsString();

			BasicAWSCredentials credential = new BasicAWSCredentials(accessKey, secretKey);
			s3 = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credential))
					.withRegion(region).build();
		} catch (FileNotFoundException e) {
			logger.warning("Could not successfully read JSON file for authentication");
			e.printStackTrace();
		}
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public BufferedImage getOriginalImage() throws IndexOutOfBoundsException, InterruptedException {
		int i = 0;
		while (getS3ImageObjects(folderName + "/original").isEmpty()) {
			logger.warning("Could not find the specified Image!");
			logger.warning("Fetching again... Attempt " + (i++));
			Thread.sleep(1000);
			if (i == 4) {
				logger.severe("Unsuccessful retrieval after " + i + " tries");
				throw new IndexOutOfBoundsException();
			}
		}
		return getS3ImageObjects(folderName + "/original").get(0);
	}

	public ArrayList<BufferedImage> getSamples() throws InterruptedException {
		return getS3ImageObjects(folderName + "/sample");
	}

	public ArrayList<BufferedImage> getS3ImageObjects(String prefix) throws InterruptedException {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		try {
			ObjectListing s3Objects = s3.listObjects(bucketName, prefix);
			ArrayList<byte[]> fileBytesList = new ArrayList<byte[]>();
			for (S3ObjectSummary summary : s3Objects.getObjectSummaries()) {
				S3Object obj = s3.getObject(bucketName, summary.getKey());
				fileBytesList.add(IOUtils.toByteArray(obj.getObjectContent()));
			}
			images.addAll(imageIOFactory.asBufferedImages(fileBytesList));

			return images;
		} catch (SdkClientException e) {
			logger.severe("Could not sucessfully retrieve from S3: " + e.toString());
		} catch (IOException e) {
			logger.severe("Coult not convernt S3 object to images");
		}
		return images;
	}

	public void uploadImage(InputStream is) {
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentType("image/png");
		s3.putObject(bucketName, folderName + "/photomosaic.png", is, meta);
	}

	public URL getResultURL() {
		return s3.getUrl(bucketName, folderName + "/photomosaic.png");
	}

	public void listAll() {
		System.out.println();
		try {
			ObjectListing s3Objects = s3.listObjects(bucketName, folderName);
			for (S3ObjectSummary summary : s3Objects.getObjectSummaries()) {
				logger.info(summary.getKey());
			}
		} catch (SdkClientException e) {
			logger.severe("Could not sucessfully retrieve from S3: " + e.toString());
		}
	}

}
