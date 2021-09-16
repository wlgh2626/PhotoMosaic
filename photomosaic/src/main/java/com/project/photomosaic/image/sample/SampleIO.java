package com.project.photomosaic.image.sample;

import java.io.File;

public class SampleIO {
	public static final String SAMPLE_DEFAULT_PATH = System.getProperty("user.dir") + "/images/sample";
	public static final String[] ACCEPTED_EXTENSIONS = new String[] { ".gif", ".png", ".tif", ".jpg" };

	private File directory;
	private String fileNames[];

	public SampleIO(String pathToSamples) throws Exception {
		directory = new File(pathToSamples);
		fileNames = directory.list((dir, name) -> matchExtension(name));
	}

	public File getDirectory() {
		return directory;
	}

	public String[] getFileNames() {
		return fileNames;
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
}
