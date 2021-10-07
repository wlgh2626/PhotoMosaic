package com.project.photomosaic.image.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.project.photomosaic.image.model.dither.DitheredImage;
import com.project.photomosaic.image.model.sample.SampleContainer;

//main class for interacting and creating photomosaic
public class Photomosaic {
	public static final String ORIGINAL_DEFAULT_PATH = System.getProperty("user.dir") + "/images/original";
	private DitheredImage dithered;
	private SampleContainer samples;
	private BufferedImage photoMosaic;
	private int length, height;

	public Photomosaic(File original, File sample) throws Exception {
		samples = new SampleContainer(sample.getPath());
		BufferedImage originalImage = ImageIO.read(original);
		
		
		int ditherSize = DitheredImage.DEFAULT_DITHER_SIZE;
		while(true) {
			ditherSize = (int) Math.ceil( (double)ditherSize * 1.5);
			length = (originalImage.getWidth()/ditherSize) * samples.getDimension();
			height = (originalImage.getHeight()/ditherSize) * samples.getDimension();
			if((long) length * (long) height < Integer.MAX_VALUE/4) {	//reduce the size if it exceeds limit
				break;
			}
		}
		
		dithered = new DitheredImage(originalImage , ditherSize);
		photoMosaic = new BufferedImage(length, height, BufferedImage.TYPE_INT_RGB);
		
	}

	public void build() {
		Graphics graphic = photoMosaic.createGraphics();
		for (int y = 0; y < dithered.getHeight(); y++) {
			for (int x = 0; x < dithered.getLength(); x++) {
				BufferedImage bestMatching = samples.getBestImage(dithered.getRGB(x, y));
				graphic.drawImage(bestMatching, x * bestMatching.getWidth(), y * bestMatching.getHeight(), null);
			}
		}
	}

	public BufferedImage getOriginalImage() {
		return dithered.getOriginal();
	}

	public BufferedImage getImage() {
		return photoMosaic;
	}
	
}
