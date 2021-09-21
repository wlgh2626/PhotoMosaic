package com.project.photomosaic.image.sample;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

//stores the image, and its average RGB value of the image
public class Sample {
	public static final int DEFAULT_DIMENSION = 40;
	
	private final BufferedImage image;
	private final long averageRGB;
	private int dimension;
	private BufferedImage downSampled;

	public Sample(String path) throws IOException {
		image = ImageIO.read(new File(path));
		dimension = DEFAULT_DIMENSION;
		long totalRGB = 0;
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				totalRGB += image.getRGB(x, y) & 0xFFFFFF ;
			}
		}
		
		long totalPixels = (image.getHeight() * image.getWidth());

		averageRGB = totalRGB / totalPixels;
		setDownSampleDim(DEFAULT_DIMENSION);
	}

	//returns the average RGB value of the sample
	public int getRGB() {
		return (int) averageRGB;
	}

	public BufferedImage getOriginal() {
		return image;
	}
	
	public int getDimension() {
		return dimension;
	}

	public void setDownSampleDim(int dimension) {
		this.dimension = dimension;
		Image temp = image.getScaledInstance( dimension , dimension , Image.SCALE_SMOOTH);
		downSampled = new BufferedImage(dimension , dimension , BufferedImage.TYPE_INT_RGB);
		Graphics g = downSampled.createGraphics();
		g.drawImage(temp , 0 , 0 ,null);
		g.dispose();
	}

	public BufferedImage getDownSampled() {
		return downSampled;
	}

	@Override
	public String toString() {
		return "Down Sample Dimension: "+ dimension + "\n" +
			    "Average RGB Value: " + averageRGB + "\n";
	}
}
