package com.project.photomosaic.image.model.photomosaic.sample;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.project.photomosaic.image.model.photomosaic.base.RGB;

//Contains all the Images used to build the original
public class SampleContainer {
	private int sampleDimension = Sample.DEFAULT_DIMENSION;
	private ArrayList<Sample> samples = new ArrayList<Sample>();

	public SampleContainer(ArrayList<BufferedImage> images) throws Exception {
		SampleThreads factory = new SampleThreads(images);
		samples.addAll(factory.asSamples());
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

			double avg = Math.sqrt(distR * distR + distG * distG + distB * distB);
			if (avg < currentBest) {
				currentBest = avg;
				index = i;
			}
		}

		return samples.get(index).getDownSampled();
	}

	public int getDimension() {
		return sampleDimension;
	}

	public ArrayList<Sample> getSampleList() {
		return samples;
	}

	@Override
	public String toString() {
		return "Total Number of Samples: " + samples.size() + "\n";
	}
}
