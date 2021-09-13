package com.project.photomosaic.image;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//Find a better name
public class DitheredImage {
	private BufferedImage originalImage;
	private SampleContainer sampleImages;
	public DitheredImage(File file) throws Exception {
		originalImage = ImageIO.read(file);
		sampleImages = new SampleContainer(SampleContainer.DEFAULT_PATH);
	}
}
