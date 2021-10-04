package com.project.photomosaic.image;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.photomosaic.image.model.CustomSearch;
import com.project.photomosaic.image.model.Photomosaic;

@RestController
public class MainController {
	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}
	
	
	@GetMapping(value = "/testImage",produces = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody byte[] test() throws IOException {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		Photomosaic photomosaic = context.getBean("testPhotomosaic" , Photomosaic.class );  
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(photomosaic.getImage() , "png", output);                          
		InputStream is = new ByteArrayInputStream(output.toByteArray());
		
		return IOUtils.toByteArray(is);
	}
	
	@GetMapping(value = "/testSearch")
	public @ResponseBody String testSearch() {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		CustomSearch search = context.getBean("testSearchEngine" , CustomSearch.class);
		
		return search.search("onion");
	}
	
	
}
