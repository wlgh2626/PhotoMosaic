package com.project.photomosaic.image.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;


/**
 * Given a path, manages the all Files intended to be used as a sample
 *
 */
public class SampleIO {
	public static final int DEFAULT_THREAD_COUNT = 2;
	public static final String SAMPLE_DEFAULT_PATH = System.getProperty("user.dir") + "/images/sample";
	public static final String[] ACCEPTED_EXTENSIONS = new String[] { ".gif", ".png", ".tif", ".jpg" };

	private File directory;
	private ArrayList<File> fileList;
	private String fileNames[];

	public SampleIO(String pathToSamples) throws Exception {
		fileList = new ArrayList<File>();
		directory = new File(pathToSamples);
		fileNames = directory.list((dir, name) -> matchExtension(name));
		for(String fileName: fileNames) {
			File file = new File(directory.getPath() + "/" + fileName);
			fileList.add(file);
		}
	}
	
	public File getDirectory() {
		return directory;
	}

	public String[] getFileNames() {
		return fileNames;
	}
	
	public ArrayList<File> getFileList(){
		return fileList;
	}

	private static boolean matchExtension(String fileName) { // returns true if the file matches any of the acccepted
		// extensions
		for (String extension : ACCEPTED_EXTENSIONS) {
			if (fileName.endsWith(extension)) {
				return true;
			}
		}
		return false;
	}
	public ArrayList<BufferedImage> generateImage() throws InterruptedException {
		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		for(File file : fileList) {
			try {
				images.add(ImageIO.read(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return images;
	}
}
