package com.project.photomosaic.image.dither;

import java.awt.image.BufferedImage;

public class DitheredImage {
	public static final int DEFAULT_DITHER_SIZE = 8;
	private int ditherSize;
	private int matrixLength;
	private int matrixHeight;

	public DitheredImage(BufferedImage image, int ditherSize) {
		matrixLength = image.getWidth();
		matrixHeight = image.getHeight();
		this.ditherSize = ditherSize;
	}

	public int getRGB(int x, int y) {
		return 0;
	}

}
