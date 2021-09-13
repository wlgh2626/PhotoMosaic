package com.project.photomosaic.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//stores the image, and its average RGB value of the image
public class Sample {
	private BufferedImage image;
	private int averageRGB;
	public Sample(String path) throws IOException {
		image = ImageIO.read(new File(path));
		//averageRGB =
	}
}
