package com.project.photomosaic.model.s3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.photomosaic.image.model.s3.S3Connector;

@Configuration
public class S3TestConfig {
	@Bean(name = "testS3Connector")
	public S3Connector getS3Connector() {
		S3Connector s3 = new S3Connector();
		return s3;
	}
}
