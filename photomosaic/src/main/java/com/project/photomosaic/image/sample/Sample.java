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
	public static final int DEFAULT_DIMENSION = 80;
	private int dimension;
	private final BufferedImage image;
	private final long averageRGB;
	private BufferedImage downSampled;

	public Sample(String path) throws IOException {
		image = ImageIO.read(new File(path));
		dimension = DEFAULT_DIMENSION;
		int totalRed = 0;
		int totalGreen = 0;
		int totalBlue = 0;
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				int rgb = image.getRGB(x, y);
				totalRed = (rgb >> 16) & 0xFF;
				totalGreen = (rgb >> 8) & 0xFF;
				totalBlue = rgb & 0xFF;
			}
		}
		long totalPixels = (image.getHeight() * image.getWidth());

		averageRGB = (totalRed / totalPixels) << 16 + (totalGreen / totalPixels) << 8 + (totalBlue / totalPixels);
		setDownSampleDim(DEFAULT_DIMENSION);
	}

	public int getAverageRGB() {
		return (int) averageRGB;
	}

	public BufferedImage getOriginal() {
		return image;
	}

	public void setDownSampleDim(int dimension) {
		Image temp = image.getScaledInstance( dimension , dimension , Image.SCALE_SMOOTH);
		downSampled = new BufferedImage(dimension , dimension , BufferedImage.TYPE_INT_RGB);
		Graphics g = downSampled.createGraphics();
		g.drawImage(temp , 0 , 0 ,null);
		g.dispose();
	}

	public BufferedImage getDownSampled() {
		return downSampled;
	}

}
