package com.project.photomosaic.image;

public class RGB {
	private int rgb;
	private int r;
	private int g;
	private int b;
	public RGB(int rgb) {
		this.rgb = rgb;
		r = (rgb >> 16) & 0xFF;
		g = (rgb >> 8) & 0xFF;
		b = rgb & 0xFF;
	}
	
	public RGB(int r , int g , int b) {
		rgb = r << 16 + g << 8 + b;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int getR() {
		return r;
	}
	
	public int getG() {
		return g;
	}
	
	public int getB() {
		return b;
	}
	
	public int getRGB() {
		return rgb;
	}
}
