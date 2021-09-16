package com.project.photomosaic.image;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.project.photomosaic.image.dither.DitheredImage;
import com.project.photomosaic.image.sample.SampleContainer;

//Find a better name
public class Photomosaic {
	public static final String ORIGINAL_DEFAULT_PATH = System.getProperty("user.dir") + "/images/original";
	private DitheredImage dithered;
	private SampleContainer sampleImages;

	private BufferedImage photoMosaic;

	public Photomosaic(File original, File sample) throws Exception {
		dithered = new DitheredImage(ImageIO.read(original));
		sampleImages = new SampleContainer(sample.getPath());
		build();
	}

	public BufferedImage getOriginalImage() {
		return dithered.getOriginalImage();
	}

	private void build() {
		photoMosaic = new BufferedImage(dithered.getLength(), dithered.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < dithered.getHeight(); y++) {
			for (int x = 0; x < dithered.getLength(); x++) {
				sampleImages.getBestImage(dithered.getRGB(x, y));
			}
		}
	}
}
