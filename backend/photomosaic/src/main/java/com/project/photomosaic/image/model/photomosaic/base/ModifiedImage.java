package com.project.photomosaic.image.model.photomosaic.base;

import java.awt.image.BufferedImage;

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
	 * @param original the original image before change
	 * @param length   the modified image length
	 * @param height   the modified image height
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
	
	/**
	 * Returns the average RGB value at the specified location of the given
	 * image. Default increment is 1, which is the slowest
	 * 
	 * @param image the target image
	 * @param xPos starting x position in target image
	 * @param yPos starting y position in target image
	 * @param length total length of the pixels to calculate from xPos
	 * @param height total height of the pixels to calculate from yPos
	 * @param increment the next pixel to be calculated will be increment distance away.
	 * @return the average RGB value of the image in unsigned 24-bit value
	 */
	protected static int getRGBAverage(BufferedImage image , int xPos , int yPos , int length , int height) {
		return getRGBAverage(image , xPos , yPos , length , height, 1);
	}
	
	protected static int getRGBAverage(BufferedImage image , int xPos , int yPos , int length , int height, int increment) {
		int size = length * height;
		float r = 0 , g = 0 , b =0;
		
		for (int y = yPos; y < yPos+height; y+=increment) {
			for (int x = xPos; x < xPos+length; x+=increment) {
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
