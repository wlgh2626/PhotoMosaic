package com.project.photomosaic.image.model.photomosaic.base;

import java.awt.image.BufferedImage;

/** 
 * Common methods among all image related classes
 */
public interface ImageInterface {
	public BufferedImage getOriginal();
	public int getHeight();
	public int getLength();
}
