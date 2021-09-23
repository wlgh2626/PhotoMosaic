package com.project.photomosaic.image.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.imageio.ImageIO;

import com.project.photomosaic.image.RGB;

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
				File file = new File(pathToSamples + "/" + fileName);
				Sample sample = new Sample(ImageIO.read(file));
				samples.add(sample);
			} catch (IOException e) {
				throw new Exception("The file: " + fileName + " could not be found in " + pathToSamples);
			}
		}
		samples.sort(Comparator.comparing(Sample::getRGB));
	}

	
	public BufferedImage getBestImage(int targetRGB) {
		RGB rgb = new RGB(targetRGB);
		int index = 0;
		double currentBest = Double.MAX_VALUE;
		for(int i = 0 ; i < samples.size() ; i++) {
			Sample sample = samples.get(i);
			double distR = rgb.getR() - sample.getR();
			double distG = rgb.getG() - sample.getG();
			double distB = rgb.getB() - sample.getB();
			double avg = Math.pow(distR, 2.0) + Math.pow(distG, 2.0) + Math.pow(distB, 2.0);
			if(avg < currentBest) {
				currentBest = avg;
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
	
	@Override
	public String toString() {
		return "File Directory: " + sampleIO.getDirectory().toPath() + "\n" + "Total Number of Samples: "
				+ sampleIO.getFileNames().length + "\n" + "Listed File names:\n"
				+ String.join("\n", sampleIO.getFileNames());
	}
}
