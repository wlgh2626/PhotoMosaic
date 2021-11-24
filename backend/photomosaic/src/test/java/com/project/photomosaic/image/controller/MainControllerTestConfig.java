package com.project.photomosaic.image.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.project.photomosaic.image.Config;
import com.project.photomosaic.image.model.s3.S3Connector;

@TestConfiguration
@Import({ Config.class })
public class MainControllerTestConfig {
	@Bean
	public MainController testMainController() {
		return new MainController();
	}

}
