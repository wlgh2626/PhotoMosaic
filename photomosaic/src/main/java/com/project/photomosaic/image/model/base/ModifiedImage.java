package com.project.photomosaic.image.model.base;

import java.awt.image.BufferedImage;

import com.project.photomosaic.image.model.RGB;

/**
 * Common functions among classes that modifies the base image
 *
 */
public abstract class ModifiedImage implements ImageInterface {

	protected BufferedImage original;
	protected int height, length;

	/**
	 * length and height is rounded up to the nearest digit
	 * 
	 * @param original Holds the original Image before modification
	 * @param length   Holds the modified image length
	 * @param height   Holds the modified image height
	 */
	public ModifiedImage(BufferedImage original, Number length, Number height) {
		this.original = original;
		this.length = (int) Math.ceil(length.doubleValue());
		this.height = (int) Math.ceil(height.doubleValue());
	}

	@Override
	public BufferedImage getOriginal() {
		return original;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getLength() {
		return length;
	}
	
	protected static int getRGBAverage(BufferedImage image , int xPos , int yPos , int length , int height) {
		int size = length * height;
		float r = 0 , g = 0 , b =0;
		
		for (int y = yPos; y < yPos+height; y++) {
			for (int x = xPos; x < xPos+length; x++) {
				if((y < image.getHeight()) && (x < image.getWidth())){
					RGB rgb = new RGB(image.getRGB(x, y));
					r += rgb.r() * rgb.r();
					g += rgb.g() * rgb.g();
					b += rgb.b() * rgb.b();
				}
			}
		}
		
		int avgR = ((int) Math.sqrt(r/size)) << 16;
		int avgG = ((int) Math.sqrt(g/size)) << 8;
		int avgB = (int) Math.sqrt(b/size);
		return avgR + avgG + avgB;
	}

}
