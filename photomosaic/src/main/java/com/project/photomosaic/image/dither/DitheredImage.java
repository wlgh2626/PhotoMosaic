package com.project.photomosaic.image.dither;

import java.awt.image.BufferedImage;

public class DitheredImage {
	public static final int DEFAULT_DITHER_SIZE = 16;
	private BufferedImage original;
	private int[][] ditherData;
	private int ditherSize;

	public DitheredImage(BufferedImage image) {
		ditherSize = DEFAULT_DITHER_SIZE;
		build(image);
	}

	public DitheredImage(BufferedImage image, int ditherSize) {
		this.ditherSize = ditherSize;
		build(image);
	}

	public void build(BufferedImage image) {
		original = image;
		int x = (int) Math.ceil((double) image.getWidth() / ditherSize);
		int y = (int) Math.ceil((double) image.getHeight() / ditherSize);
		ditherData = new int[y][x];

		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				ditherData[j % ditherSize][i % ditherSize] += image.getRGB(i, j) / ditherSize;
			}
		}
	}

	public int getSize() {
		return ditherSize;
	}

	public int getHeight() {
		return ditherData.length;
	}

	public int getLength() {
		return ditherData[0].length;
	}

	public int getRGB(int x, int y) {
		return ditherData[y][x];
	}

	public BufferedImage getOriginalImage() {
		return original;
	}

}
