package com.project.photomosaic.image.model.photomosaic.sample;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.project.photomosaic.image.model.photomosaic.base.ModifiedImage;
import com.project.photomosaic.image.model.photomosaic.base.RGB;

/**
 * Takes in a buffered image, and stores its total average RGB and its
 * down-sampled version of the image.
 * 
 * @author Jiho
 *
 */
public class Sample extends ModifiedImage {
	public static final int DEFAULT_DIMENSION = 40; // Bigger takes longer time to get AverageRGB

	private final RGB rgb;
	private int dimension;
	private BufferedImage downSampled;

	public Sample(BufferedImage image) {
		super(image);
		dimension = DEFAULT_DIMENSION;
		setDimension(dimension);
		rgb = new RGB(getRGBAverage(image, 0, 0, image.getWidth(), image.getHeight(), 8));
	}

	public BufferedImage getDownSampled() {
		return downSampled;
	}

	public RGB getRGB() {
		return rgb;
	}

	@Override
	public String toString() {
		return "Down Sample Dimension: " + dimension + "\n" + "Average RGB Value: " + rgb.rgb() + "\n";
	}

	public int getDimension() {
		return dimension;
	}

	/**
	 * resizes the down sample to the specified dimension. the down-sample is always
	 * a square.
	 * 
	 * @param dimension the desired length and width of the resized image
	 */
	public void setDimension(int dimension) {
		this.dimension = dimension;
		Image temp = getOriginal().getScaledInstance(dimension, dimension, Image.SCALE_SMOOTH);
		downSampled = new BufferedImage(dimension, dimension, BufferedImage.TYPE_INT_RGB);
		Graphics g = downSampled.createGraphics();
		g.drawImage(temp, 0, 0, null);
		g.dispose();
	}

}
