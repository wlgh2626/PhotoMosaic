package com.project.photomosaic.image.model.photomosaic.base;

/**
 * Extracts the unsigned 24-bit RGB value from the given number
 * Or sums R,G,B values as unsigned 24-bit RGB sum
 */
public class RGB {
	private int rgb;
	private int r;
	private int g;
	private int b;
	
	/**
	 * @param rgb Unsigned 24-bit RGB value, 8-bit depth each
	 */
	public RGB(int rgb) {
		this.rgb = rgb;
		r = (rgb >> 16) & 0xFF;
		g = (rgb >> 8) & 0xFF;
		b = rgb & 0xFF;
	}
	
	/**
	 * 
	 * @param r unsigned 8-bit red value
	 * @param g unsigned 8-bit green value
	 * @param b unsigned 8-bit blue value
	 */
	public RGB(int r , int g , int b) {
		rgb = r << 16 + g << 8 + b;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int r() {
		return r;
	}
	
	public int g() {
		return g;
	}
	
	public int b() {
		return b;
	}
	
	public int rgb() {
		return rgb;
	}
}
