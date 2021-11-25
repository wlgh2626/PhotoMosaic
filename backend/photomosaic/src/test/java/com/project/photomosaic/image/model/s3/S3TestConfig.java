package com.project.photomosaic.image.model.s3;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.project.photomosaic.image.config.Config;
import com.project.photomosaic.image.model.s3.S3Connector;

@TestConfiguration
@Import({ Config.class })
public class S3TestConfig {
	@Bean(name = "testS3Connector")
	public S3Connector getS3Connector() {
		S3Connector s3 = new S3Connector();
		return s3;
	}
}
