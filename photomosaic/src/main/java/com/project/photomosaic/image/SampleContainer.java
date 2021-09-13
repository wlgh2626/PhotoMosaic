package com.project.photomosaic.image;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//Contains all the Images used to build the original
public class SampleContainer {
	public static final String DEFAULT_PATH = System.getProperty("user.dir") + "/images/sample";
	public static final String[] ACCEPTED_EXTENSIONS = new String[] {
			".gif" , ".png" , ".tif"
	};
	
	private File directory;
	private ArrayList<Sample> samples;
	private String fileNames[];
	public SampleContainer(String pathToSamples) throws Exception  {
		samples = new ArrayList<Sample>();
		directory = new File(pathToSamples);
		fileNames = directory.list((dir , name) -> matchExtension(name));
		//fileNames = directory.list();
		for(String fileName : fileNames) {
			try {
				samples.add(new Sample(pathToSamples + "/" + fileName));
			} catch (IOException e) {
				throw new Exception("The file: " + fileName + " could not be found in " + pathToSamples);
			}
		}
	}
	
	//return the image best matching the RGB Value
	public int getBest(int RGB) {
		return 0;
	}
	
	public int getBest(int r , int g ,int b) {
		return 0;
	}
	
	public File getDirectory() {
		return directory;
	}
	
	public String[] getFileNames() {
		return fileNames;
	}
	
	@Override
	public String toString() {
		return "File Directory: " + directory.toPath() + "\n" +
			   "Total Number of Samples: " + fileNames.length + "\n" +
			   "Listed File names:\n" + String.join("\n", fileNames);
	}
	
	private static boolean matchExtension(String fileName) {	//returns true if the file matches any of the acccepted extensions
		for(String extension : ACCEPTED_EXTENSIONS) {
			if(fileName.endsWith(extension)) {
				return true;
			}
		}
		return false;
	}
}
