package com.project.photomosaic.image.model.photomosaic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.project.photomosaic.image.config.Config;
import com.project.photomosaic.image.model.photomosaic.Photomosaic;
import com.project.photomosaic.image.model.utils.ImageIOThreads;

@TestConfiguration
@Import({ Config.class })
public class PhotomosaicTestConfig {
	public static final String ORIGINAL_DEFAULT_PATH = System.getProperty("user.dir") + "/images/original";
	public static final String SAMPLE_DEFAULT_PATH = System.getProperty("user.dir") + "/images/sample/test";
	public static final String[] ACCEPTED_EXTENSIONS = new String[] { ".gif", ".png", ".tif", ".jpg" };
	public static File DUCK = new File(ORIGINAL_DEFAULT_PATH + "/test/duck.jpg");
	public static File LENA = new File(ORIGINAL_DEFAULT_PATH + "/test/lena.tif");

	@Bean(name = "singleCore")
	public ImageIOThreads singleImageIOThreads() {
		return new ImageIOThreads(1);
	}

	@Bean(name = "multiCore")
	public ImageIOThreads multiImageIOThreads() {
		return new ImageIOThreads(Runtime.getRuntime().availableProcessors());
	}

	@Bean(name = "testFiles")
	public ArrayList<File> testFiles() {
		File targetPath = new File(SAMPLE_DEFAULT_PATH);
		File[] files = targetPath.listFiles();
		return new ArrayList<File>(Arrays.asList(files));
	}

	@Bean(name = "testFileBytesList")
	public ArrayList<byte[]> fileBytesList() throws IOException {
		ArrayList<byte[]> fileBytesList = new ArrayList<byte[]>();
		for (File file : testFiles()) {
			fileBytesList.add(FileUtils.readFileToByteArray(file));
		}
		return fileBytesList;
	}

	@Bean(name = "duckImage")
	public BufferedImage duckImage() throws IOException {
		return ImageIO.read(DUCK);
	}

}
