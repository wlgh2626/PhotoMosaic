package com.project.photomosaic.image.sample;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.project.photomosaic.image.base.BaseImage;
import com.project.photomosaic.image.base.ColorModel;


//stores the image, and its average RGB value of the image
public class Sample extends BaseImage{
	public static final int DEFAULT_DIMENSION = 40;
	
	private final long averageRGB;
	private final long averageYUV;
	private int dimension;
	private BufferedImage downSampled;

	public Sample(BufferedImage image){
		super(image, DEFAULT_DIMENSION , DEFAULT_DIMENSION);
		dimension = DEFAULT_DIMENSION;
		setDimension(dimension);
		
		long totalRGB = 0;
		long totalYUV = 0;
		for (int y = 0; y < original.getHeight(); y++) {
			for (int x = 0; x < original.getWidth(); x++) {
				int rgb = original.getRGB(x, y) & 0xFFFFFF;
				totalRGB += rgb;
				totalYUV += ColorModel.RGBToYUV(rgb);
			}
		}
	
		averageRGB = totalRGB / (original.getHeight() * original.getWidth());
		averageYUV = totalYUV / (original.getHeight() * original.getWidth());
	}

	public BufferedImage getDownSampled() {
		return downSampled;
	}
	
	public int getRGB() {
		return (int) averageRGB;
	}
	
	public int getYUV() {
		return (int) averageYUV;
	}
	
	public int getR() {
		return (((int) averageRGB) >> 16) & 0xFF;
	}
	
	public int getG() {
		return (((int) averageRGB) >> 8) & 0xFF;
	}
	
	public int getB() {
		return ((int) averageRGB) & 0xFF;
	}

	@Override
	public String toString() {
		return "Down Sample Dimension: "+ dimension + "\n" +
			    "Average RGB Value: " + averageRGB + "\n";
	}
	
	/**
	 * resizes the down sample to the specified dimension.
	 * the down sample is always a square.
	 * @param dimension specifies the dimension of the down sampled version of the image
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
		Image temp = original.getScaledInstance( dimension , dimension , Image.SCALE_SMOOTH);
		downSampled = new BufferedImage(dimension , dimension , BufferedImage.TYPE_INT_RGB);
		Graphics g = downSampled.createGraphics();
		g.drawImage(temp , 0 , 0 ,null);
		g.dispose();
	}

}
