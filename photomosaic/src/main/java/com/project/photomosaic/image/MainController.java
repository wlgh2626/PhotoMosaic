package com.project.photomosaic.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
		ArrayList<BufferedImage> images = cse.searchImage(searchQuery);
	
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		Photomosaic photomosaic = context.getBean("photomosaic" , Photomosaic.class);
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(photomosaic.getImage() , "png", output);
		InputStream is = new ByteArrayInputStream(output.toByteArray());
		
		return IOUtils.toByteArray(is);
	}
	
	
}
