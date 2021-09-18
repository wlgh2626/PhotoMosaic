package com.project.photomosaic.image.sample;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

//Contains all the Images used to build the original
public class SampleContainer {
	int sampleDimension;
	private ArrayList<Sample> samples;
	SampleIO sampleIO;

	public SampleContainer(String pathToSamples) throws Exception {
		sampleDimension = Sample.DEFAULT_DIMENSION;
		samples = new ArrayList<Sample>();
		sampleIO = new SampleIO(pathToSamples);
		

		for (String fileName : sampleIO.getFileNames()) {
			try {
				samples.add(new Sample(pathToSamples + "/" + fileName));
			} catch (IOException e) {
				throw new Exception("The file: " + fileName + " could not be found in " + pathToSamples);
			}
		}
	}

	// return the image best matching the RGB Value
	public BufferedImage getBestImage(int RGB) {
		return samples.get(0).getDownSampled();
	}

	public String[] getFileNames() {
		return sampleIO.getFileNames();
	}
	
	public int getSampleDimension() {
		return sampleDimension;
	}
	
	public ArrayList<Sample> getSampleList(){
		return samples;
	}

	@Override
	public String toString() {
		return "File Directory: " + sampleIO.getDirectory().toPath() + "\n" + "Total Number of Samples: "
				+ sampleIO.getFileNames().length + "\n" + "Listed File names:\n"
				+ String.join("\n", sampleIO.getFileNames());
	}
}
