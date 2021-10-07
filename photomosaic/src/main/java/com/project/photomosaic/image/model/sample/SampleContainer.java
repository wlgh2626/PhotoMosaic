package com.project.photomosaic.image.model.sample;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.project.photomosaic.image.model.RGB;

//Contains all the Images used to build the original
public class SampleContainer {
	private int sampleDimension = Sample.DEFAULT_DIMENSION;
	private ArrayList<Sample> samples =  new ArrayList<Sample>();
	private SampleIO sampleIO;

	public SampleContainer(String pathToSamples) throws Exception {
		ImageIO.setUseCache(false);
		sampleIO = new SampleIO(pathToSamples);
		ArrayList<BufferedImage> images = sampleIO.generateImage();
		
		SampleFactory factory = new SampleFactory(images);
		samples.addAll(factory.generateSamples());
	}

	public BufferedImage getBestImage(int targetRGB) {
		RGB rgb = new RGB(targetRGB);
		int index = 0;
		double currentBest = Double.MAX_VALUE;
		for (int i = 0; i < samples.size(); i++) {
			Sample sample = samples.get(i);
			double distR = rgb.r() - sample.getRGB().r();
			double distG = rgb.g() - sample.getRGB().g();
			double distB = rgb.b() - sample.getRGB().b();
			
			double avg = Math.sqrt( distR*distR + distG*distG + distB*distB);
			if (avg < currentBest) {
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

	public ArrayList<Sample> getSampleList() {
		return samples;
	}

	@Override
	public String toString() {
		return "File Directory: " + sampleIO.getDirectory().toPath() + "\n" + "Total Number of Samples: "
				+ sampleIO.getFileNames().length + "\n" + "Listed File names:\n"
				+ String.join("\n", sampleIO.getFileNames());
	}
}
