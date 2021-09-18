package com.project.photomosaic.image;

import java.awt.Graphics;
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

	private void build() {
		int photoLength = dithered.getLength()*sampleImages.getSampleDimension();
		int photoHeight = dithered.getHeight()*sampleImages.getSampleDimension();
		photoMosaic = new BufferedImage( photoLength, photoHeight, BufferedImage.TYPE_INT_RGB);
		
		Graphics graphic = photoMosaic.createGraphics();
		for (int y = 0; y < dithered.getHeight(); y++) {
			for (int x = 0; x < dithered.getLength(); x++) {
				BufferedImage bestMatching = sampleImages.getBestImage(dithered.getRGB(x, y));
				graphic.drawImage(bestMatching , x*dithered.getSize() , y*dithered.getSize() , null);
			}
		}
	}

	
	public BufferedImage getOriginalImage() {
		return dithered.getOriginalImage();
	}
	
	public BufferedImage getImage() {
		return photoMosaic;
	}
}
