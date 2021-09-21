package com.project.photomosaic.image.dither;

import java.awt.image.BufferedImage;

public class DitheredImage {
	public static final int DEFAULT_DITHER_SIZE = 16;
	private BufferedImage original;
	private BufferedImage dithered;
	private long[][] data;
	private int size;	//original image gets sectioned to (size x size)

	public DitheredImage(BufferedImage image) {
		original = image;
		size = DEFAULT_DITHER_SIZE;
		build();
	}

	public DitheredImage(BufferedImage image, int ditherSize) {
		original = image;
		this.size = ditherSize;
		build();
	}

	private void build() {
		int length = (int) Math.ceil((double) original.getWidth() / size);
		int height = (int) Math.ceil((double) original.getHeight() / size);
		data = new long[height][length];

		for(int y = 0 ; y < original.getHeight() ; y++) {
			for(int x = 0 ; x < original.getWidth() ; x++) {
				int RGB = (original.getRGB(x, y) & 0xFFFFFF);
				data[y/size][x/size] += RGB / (size*size);	//NOT CORRECT
			}
		}
		
		dithered = new BufferedImage(length*size , height*size ,  BufferedImage.TYPE_INT_RGB);
		for(int y = 0 ; y < dithered.getHeight() ; y++) {
			for(int x = 0 ; x < dithered.getWidth() ; x++){
				int RGB = (int) data[y/size][x/size];
				dithered.setRGB( x, y, RGB);
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
		return (int) data[y][x];
	}

	public BufferedImage getOriginalImage() {
		return original;
	}
	
	//returns image with SizeXSize block average pixel of that section
	public BufferedImage getDitheredImage() {
		return dithered;
	}
	
	public String toString() {
		return "Original Image Information: " + original + "\n" +
			   "Number of Horizontal Dither Matrix (Length): " + data.length + "\n" +
			   "Number of Vertical Dither Matrix (Height): " + data[0].length + "\n"+ 
			   "Dithered Image Number of Pixels: " + data.length*data[0].length + "\n"+
			   "Dithering Size: " + size + "\n";
				
	}

}
