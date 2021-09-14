package com.project.photomosaic.image.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//stores the image, and its average RGB value of the image
public class Sample {
	private BufferedImage image;
	private long averageRGB;

	public Sample(String path) throws IOException {
		image = ImageIO.read(new File(path));

		long totalRGB = 0;
		for (int y = 0; y < image.getHeight(); y++) {
			for (int x = 0; x < image.getWidth(); x++) {
				totalRGB += image.getRGB(x, y);
			}
		}
		averageRGB = totalRGB / (image.getHeight() * image.getWidth());
	}

	public int getAverageRGB() {
		return (int) averageRGB;
	}

	public BufferedImage getImage() {
		return image;
	}
}
