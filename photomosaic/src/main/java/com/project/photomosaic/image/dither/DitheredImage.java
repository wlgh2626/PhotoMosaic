package com.project.photomosaic.image.dither;

import java.awt.image.BufferedImage;
import com.project.photomosaic.image.base.ModifiedImage;

public class DitheredImage extends ModifiedImage {
	public static final int DEFAULT_DITHER_SIZE = 2;	//Will tile every 16x16 of the image
	private BufferedImage dithered;
	private long[][] data;
	private int ditherSize; // original image gets sectioned to (size x size)

	public DitheredImage(BufferedImage image) {
		super(image, (double) image.getWidth() / DEFAULT_DITHER_SIZE, (double) image.getHeight() / DEFAULT_DITHER_SIZE);
		ditherSize = DEFAULT_DITHER_SIZE;
		build();
	}

	public DitheredImage(BufferedImage image, int ditherSize) {
		super(image, (double) image.getWidth() / ditherSize, (double) image.getHeight() / ditherSize);
		this.ditherSize = ditherSize;
		build();
	}

	private void build() {
		data = new long[height][length];
		for (int y = 0; y < getOriginal().getHeight(); y+= ditherSize) {
			for (int x = 0; x < getOriginal().getWidth(); x+= ditherSize) {
				data[y / ditherSize][x / ditherSize] = getRGBAverage(getOriginal() , x , y , ditherSize , ditherSize);
			}
		}
		
		dithered = new BufferedImage(length * ditherSize, height * ditherSize, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < dithered.getHeight(); y++) {
			for (int x = 0; x < dithered.getWidth(); x++) {
				int rgb = (int) data[y / ditherSize][x / ditherSize];
				dithered.setRGB(x, y, rgb);
			}
		}
	}

	public int getSize() {
		return ditherSize;
	}

	public int getRGB(int x, int y) {
		return (int)data[y][x];
	}

	public BufferedImage getDitheredImage() {
		return dithered;
	}

	public String toString() {
		return "Original Image Information: " + getOriginal() + "\n" + 
				"Number of Horizontal Dither Matrix (Length): " + length + "\n" + 
				"Number of Vertical Dither Matrix (Height): " + height + "\n" + 
				"Dithered Image Number of Pixels: " + length * height + "\n" + 
				"Dithering Size: " + ditherSize + "\n";

	}
	
}
