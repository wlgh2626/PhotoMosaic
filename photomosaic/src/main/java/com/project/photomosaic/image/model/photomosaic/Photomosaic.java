package com.project.photomosaic.image.model.photomosaic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.project.photomosaic.image.model.photomosaic.dither.DitheredImage;
import com.project.photomosaic.image.model.photomosaic.sample.SampleContainer;

//main class for interacting and creating photomosaic
public class Photomosaic {
	public static final String ORIGINAL_DEFAULT_PATH = System.getProperty("user.dir") + "/images/original";
	
	private BufferedImage originalImage;
	private DitheredImage dithered;
	private SampleContainer sampleContainer;
	private BufferedImage photoMosaic;
	private int length, height;

	public Photomosaic(File original, File sample) throws Exception {
		sampleContainer = new SampleContainer(sample.getPath());
		originalImage = ImageIO.read(original);
		build();
	}

	public Photomosaic(BufferedImage originalImage, ArrayList<BufferedImage> samples) throws Exception {
		sampleContainer = new SampleContainer(samples);
		this.originalImage = originalImage;
		build();
	}

	private void build() {
		int ditherSize = DitheredImage.DEFAULT_DITHER_SIZE;
		
		/*
		 * Reduce the size if the resulting image will exceed BufferedImage size limit
		 */
		while(true) {
			ditherSize = (int) Math.ceil( (double)ditherSize * 1.5);
			length = (originalImage.getWidth()/ditherSize) * sampleContainer.getDimension();
			height = (originalImage.getHeight()/ditherSize) * sampleContainer.getDimension();
			if((long) length * (long) height < Integer.MAX_VALUE/4) {
				break;
			}
		}
		
		dithered = new DitheredImage(originalImage , ditherSize);
		photoMosaic = new BufferedImage(length, height, BufferedImage.TYPE_INT_RGB);
		
		Graphics graphic = photoMosaic.createGraphics();
		for (int y = 0; y < dithered.getHeight(); y++) {
			for (int x = 0; x < dithered.getLength(); x++) {
				BufferedImage bestMatching = sampleContainer.getBestImage(dithered.getRGB(x, y));
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
