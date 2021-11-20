package com.project.photomosaic.image.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.photomosaic.image.model.Config;
import com.project.photomosaic.image.model.cse.CustomSearch;
import com.project.photomosaic.image.model.photomosaic.Photomosaic;
import com.project.photomosaic.image.model.s3.S3Connector;

@RestController
@ContextConfiguration(classes = { Config.class })
public class MainController {
	S3Connector s3 = new S3Connector();

	@RequestMapping(value = "/api/**", method = RequestMethod.OPTIONS)
	public void corsHeaders(HttpServletResponse response) {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with");
		response.addHeader("Access-Control-Max-Age", "72000");
	}

	@Autowired
	@Qualifier("search")
	private CustomSearch cse;

	@GetMapping(value = "/ping")
	@CrossOrigin
	@ResponseBody
	public String ping() {
		return "pong";
	}

	@GetMapping(value = "/photomosaic")
	@CrossOrigin
	@ResponseBody
	public String request(@RequestParam(name = "s3") String s3FolderName) throws Exception {
		try {
			s3.setFolderName(s3FolderName);
			s3.listAll();

			BufferedImage original = s3.getOriginalImage();
			ArrayList<BufferedImage> samples = s3.getSamples();

			Photomosaic photomosaic = new Photomosaic(original, samples);

			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(photomosaic.getImage(), "png", os);
			InputStream is = new ByteArrayInputStream(os.toByteArray());
			s3.uploadImage(is);

			original.flush();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
			return "Server couldnt successfully photo mosaic!";
		}
		return s3.getResultURL().toString();
	}

}
