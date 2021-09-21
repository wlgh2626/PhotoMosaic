package com.project.photomosaic.image.dither;

import java.awt.image.BufferedImage;

public class DitheredImage {
	public static final int DEFAULT_DITHER_SIZE = 16;
	private BufferedImage original;
	private long[][] data;
	private int size;	//original image gets sectioned to (size x size)

	public DitheredImage(BufferedImage image) {
		size = DEFAULT_DITHER_SIZE;
		build(image);
	}

	public DitheredImage(BufferedImage image, int ditherSize) {
		this.size = ditherSize;
		build(image);
	}

	public void build(BufferedImage image) {
		original = image;
		int length = (int) Math.ceil((double) original.getWidth() / size);
		int height = (int) Math.ceil((double) original.getHeight() / size);
		data = new long[height][length];

		for(int y = 0 ; y < original.getHeight() ; y++) {
			for(int x = 0 ; x < original.getWidth() ; x++) {
				data[y % height][x % length] += original.getRGB(x, y) & 0xFFFFFF;
			}
		}
	}

	public int getSize() {
		return size;
	}

	public int getHeight() {
		return data.length;
	}

	public int getLength() {
		return data[0].length;
	}

	public int getRGB(int x, int y) {
		return (int)( data[y][x] / (size*size));
	}

	public BufferedImage getOriginalImage() {
		return original;
	}

}
