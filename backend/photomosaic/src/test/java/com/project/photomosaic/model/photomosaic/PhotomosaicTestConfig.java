package com.project.photomosaic.model.photomosaic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.photomosaic.image.model.photomosaic.Photomosaic;
import com.project.photomosaic.image.model.utils.ImageThreads;

@Configuration
public class PhotomosaicTestConfig {
	public static final String SAMPLE_DEFAULT_PATH = System.getProperty("user.dir") + "/images/sample/test";
	public static final String[] ACCEPTED_EXTENSIONS = new String[] { ".gif", ".png", ".tif", ".jpg" };
	public static File DUCK = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/duck.jpg");
	public static File LENA = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/lena.tif");

	@Bean(name = "singleCore")
	public ImageThreads getSingleImageIOFactory() {
		return new ImageThreads(1);
	}

	@Bean(name = "multiCore")
	public ImageThreads getMultiImageIOFactory() {
		return new ImageThreads(Runtime.getRuntime().availableProcessors());
	}

	@Bean(name = "testFiles")
	public ArrayList<File> getTestFiles() {
		File targetPath = new File(SAMPLE_DEFAULT_PATH);
		File[] files = targetPath.listFiles();
		return new ArrayList<File>(Arrays.asList(files));
	}

	@Bean(name = "testFileBytesList")
	public ArrayList<byte[]> getFileBytesList() throws IOException {
		ArrayList<byte[]> fileBytesList = new ArrayList<byte[]>();
		for (File file : getTestFiles()) {
			fileBytesList.add(FileUtils.readFileToByteArray(file));
		}
		return fileBytesList;
	}

	@Bean(name = "duckImage")
	public BufferedImage getDuckImage() throws IOException {
		return ImageIO.read(DUCK);
	}

	@Bean(name = "sampleImages")
	public ArrayList<BufferedImage> getSampleImages() throws InterruptedException, IOException {
		ImageThreads factory = getMultiImageIOFactory();
		return factory.asBufferedImages(getFileBytesList());
	}

}
