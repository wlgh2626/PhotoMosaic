package com.project.photomosaic.image.model.photomosaic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.project.photomosaic.image.model.photomosaic.dither.DitheredImage;
import com.project.photomosaic.image.model.photomosaic.sample.SampleContainer;

/**
 * Creates a photomosaic of a given image, with the given sample images
 * 
 * @author Jiho
 *
 */
public class Photomosaic {
	public static final int MAX_IMAGE_SIZE = Integer.MAX_VALUE / 4;

	private BufferedImage originalImage, photoMosaic;
	private DitheredImage dithered;
	private SampleContainer sampleContainer;
	private int ditherSize;

	/**
	 * 
	 * @param originalImage image to be photo mosaiced.
	 * @param sampleImages  images that will replace the original image tile by
	 *                      tile.
	 * @param ditherSize    size of the each tiles that will be replaced
	 * @throws Exception
	 */
	public Photomosaic(BufferedImage originalImage, ArrayList<BufferedImage> sampleImages, int ditherSize)
			throws Exception {
		sampleContainer = new SampleContainer(sampleImages);
		this.originalImage = originalImage;
		this.ditherSize = ditherSize;
	}

	/**
	 * method that generates the photo mosaic image.
	 */
	public void build() {
		while ((long) getLength(ditherSize) * (long) getHeight(ditherSize) > MAX_IMAGE_SIZE) {
			ditherSize++;
		}

		dithered = new DitheredImage(originalImage, ditherSize);
		photoMosaic = new BufferedImage(getLength(ditherSize), getHeight(ditherSize), BufferedImage.TYPE_INT_RGB);

		Graphics graphic = photoMosaic.createGraphics();
		for (int y = 0; y < dithered.getHeight(); y++) {
			for (int x = 0; x < dithered.getLength(); x++) {
				BufferedImage bestMatching = sampleContainer.getBestImage(dithered.getRGB(x, y));
				graphic.drawImage(bestMatching, x * bestMatching.getWidth(), y * bestMatching.getHeight(), null);
			}
		}
	}

	private int getLength(int size) {
		return (originalImage.getWidth() / size) * sampleContainer.getDimension();
	}

	private int getHeight(int size) {
		return (originalImage.getHeight() / size) * sampleContainer.getDimension();
	}

	public BufferedImage getOriginalImage() {
		return dithered.getOriginal();
	}

	public BufferedImage getImage() {
		return photoMosaic;
	}

}
