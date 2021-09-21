package com.project.photomosaic.image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.project.photomosaic.image.dither.DitheredImage;
import com.project.photomosaic.image.sample.SampleContainer;

//main class for interacting and creating photomosaic
public class Photomosaic {
	public static final String ORIGINAL_DEFAULT_PATH = System.getProperty("user.dir") + "/images/original";
	private DitheredImage dithered;
	private SampleContainer samples;
	private BufferedImage photoMosaic;

	public Photomosaic(File original, File sample) throws Exception {
		dithered = new DitheredImage(ImageIO.read(original));
		samples = new SampleContainer(sample.getPath());
		build();
	}

	private void build() {
		int length = dithered.getLength()*samples.getDimension();
		int height = dithered.getHeight()*samples.getDimension();
		photoMosaic = new BufferedImage( length, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics graphic = photoMosaic.createGraphics();
		for (int y = 0; y < dithered.getHeight(); y++) {
			for (int x = 0; x < dithered.getLength(); x++) {
				BufferedImage bestMatching = samples.getBestImage(dithered.getRGB(x, y) & 0xFFFFFF);
				graphic.drawImage(bestMatching , x*bestMatching.getWidth() , y*bestMatching.getHeight() , null);
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
