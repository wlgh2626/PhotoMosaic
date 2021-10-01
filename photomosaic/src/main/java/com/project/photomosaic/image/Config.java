package com.project.photomosaic.image;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.project.photomosaic.image.model.Photomosaic;
import com.project.photomosaic.image.model.sample.SampleIO;

@Configuration
@ComponentScan(basePackageClasses = Photomosaic.class)
public class Config {
	
	@Bean(name = "testPhotomosaic")
	public Photomosaic getTestPhotomosaic() throws Exception {
		File LENA = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/lena.tif");
		File SAMPLE = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test");
		return new Photomosaic(LENA , SAMPLE);
	}
}
