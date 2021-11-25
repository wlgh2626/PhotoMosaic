package com.project.photomosaic.image.controller;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import com.project.photomosaic.image.config.Config;

@TestConfiguration
@Import({ Config.class })
public class MainControllerTestConfig {
	@Bean
	public MainController testMainController() {
		return new MainController();
	}

}
