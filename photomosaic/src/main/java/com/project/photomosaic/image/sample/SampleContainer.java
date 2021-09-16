package com.project.photomosaic.image.sample;

import java.io.IOException;
import java.util.ArrayList;

//Contains all the Images used to build the original
public class SampleContainer {
	private ArrayList<Sample> samples;
	SampleIO sampleIO;

	public SampleContainer(String pathToSamples) throws Exception {
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
	public int getBestImage(int RGB) {
		return 0;
	}

	@Override
	public String toString() {
		return "File Directory: " + sampleIO.getDirectory().toPath() + "\n" + "Total Number of Samples: "
				+ sampleIO.getFileNames().length + "\n" + "Listed File names:\n"
				+ String.join("\n", sampleIO.getFileNames());
	}

	public String[] getFileNames() {
		return sampleIO.getFileNames();
	}

}
