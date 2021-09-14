package com.project.photomosaic;

import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageDisplay {
	private ImageView imageView;

	public ImageDisplay(BufferedImage bufferedImage) {
		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
		imageView = new ImageView(image);
		if ((bufferedImage.getWidth() > 1200) || (bufferedImage.getHeight() > 1200)) {
			imageView.setFitWidth(bufferedImage.getWidth() / 4);
			imageView.setFitHeight(bufferedImage.getHeight() / 4);
		}
	}

	public ImageView getImageView() {
		return imageView;
	}
}
