package com.project.photomosaic.image;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.project.photomosaic.image.dither.DitheredImage;
import com.project.photomosaic.image.sample.SampleContainer;

//Find a better name
public class Photomosaic {
	public static final String ORIGINAL_DEFAULT_PATH = System.getProperty("user.dir") + "/images/original";
	private BufferedImage originalImage;
	private DitheredImage dithered;
	private SampleContainer sampleImages;

	public Photomosaic(File original, File sample) throws Exception {
		originalImage = ImageIO.read(original);
		dithered = new DitheredImage(originalImage, DitheredImage.DEFAULT_DITHER_SIZE);
		sampleImages = new SampleContainer(sample.getPath());
	}

	public BufferedImage getOriginalImage() {
		return originalImage;
	}

}
