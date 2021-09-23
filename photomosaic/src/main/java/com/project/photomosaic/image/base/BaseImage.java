package com.project.photomosaic.image.base;

import java.awt.image.BufferedImage;


public abstract class BaseImage implements ImageInterface{
	
	public BufferedImage original;
	public int height, length;
	
	public BaseImage(BufferedImage original, int length , int height) {
		this.original = original;
		this.length = length;
		this.height = height;
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

}
