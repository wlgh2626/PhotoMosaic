package com.project.photomosaic.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.photomosaic.image.model.CustomSearch;
import com.project.photomosaic.image.model.Photomosaic;
import com.project.photomosaic.image.model.sample.SampleIO;

@RestController
@ContextConfiguration(classes = {Config.class} )
public class MainController {
	@Autowired
	@Qualifier("search")
	private CustomSearch cse;
	
	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}
	
	/*
	@GetMapping(value = "/searchimage", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] searchImage( @RequestParam(name = "q") String searchQuery) throws IOException {
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(photomosaic.getImage() , "png", output);                          
		InputStream is = new ByteArrayInputStream(output.toByteArray());
		
		return IOUtils.toByteArray(is);
	}
	*/
	
	@GetMapping(value = "/search", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] search(@RequestParam(name = "q") String searchQuery) throws IOException {
		int i = 0;
		for(BufferedImage image : cse.searchImage(searchQuery)) {
			SampleIO.write( image, "sample" + (i++));
		}
		
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		Photomosaic photomosaic = context.getBean("photomosaic" , Photomosaic.class);
		photomosaic.build();
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(photomosaic.getImage() , "png", output);
		InputStream is = new ByteArrayInputStream(output.toByteArray());
		
		return IOUtils.toByteArray(is);
	}
	
	@GetMapping(value = "/request", produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] request(@RequestParam(name = "s3") String s3URL) throws IOException {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		Photomosaic photomosaic = context.getBean("photomosaic" , Photomosaic.class);
		photomosaic.build();
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(photomosaic.getImage() , "png", output);
		InputStream is = new ByteArrayInputStream(output.toByteArray());
		
		return IOUtils.toByteArray(is);
	}
	
	
}
