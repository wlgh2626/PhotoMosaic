package com.project.photomosaic.image.sample;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

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
		samples.sort(Comparator.comparing(Sample::getRGB));
	}

	// return the image best matching the RGB Value
	public BufferedImage getBestImage(int targetRGB) {
		int[] samplesRGB = samples.stream().mapToInt(Sample::getRGB).toArray();
		
		int index = 0;
		int currentBest = Integer.MAX_VALUE;
		for(int i = 0 ; i < samplesRGB.length ; i++) {
			if(Math.abs(samplesRGB[i] - targetRGB) < currentBest) {
				currentBest = samplesRGB[i];
				index = i;
			}
		}
		
		return samples.get(index).getDownSampled();
	}

	public String[] getFileNames() {
		return sampleIO.getFileNames();
	}
	
	public int getDimension() {
		return sampleDimension;
	}
	
	public ArrayList<Sample> getSampleList(){
		return samples;
	}
	
	private int binarySearch(int[] samplesRGB , int targetRGB) {
		int index = Arrays.binarySearch( samplesRGB , targetRGB);
		if(index < 0) {
			index = (index*-1) - 2;
		} 
		
		if(index < 0) {
			index++;
		}
		return index;
	}

	@Override
	public String toString() {
		return "File Directory: " + sampleIO.getDirectory().toPath() + "\n" + "Total Number of Samples: "
				+ sampleIO.getFileNames().length + "\n" + "Listed File names:\n"
				+ String.join("\n", sampleIO.getFileNames());
	}
}
