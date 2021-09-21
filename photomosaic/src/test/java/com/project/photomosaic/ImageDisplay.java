package com.project.photomosaic;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ImageDisplay {
	public static final ArrayList<String> ORIGINAL_TEST_FILES = new ArrayList<String>() {
		{
			add("duck.jpg");
		}
	};
	
	private ImageView imageView;

	public ImageDisplay(BufferedImage bufferedImage) {
		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
		imageView = new ImageView(image);
		
		double downScale = 1 ; 
		while ((bufferedImage.getWidth()/downScale > 1200) || (bufferedImage.getHeight()/downScale > 1200)) {
			downScale *= 1.5;
		}
		imageView.setFitWidth(bufferedImage.getWidth() / downScale);
		imageView.setFitHeight(bufferedImage.getHeight() / downScale);
	}

	public ImageView getImageView() {
		return imageView;
	}
	
	public Stage getStage() {
		Stage stage = new Stage();
		HBox hbox = new HBox(imageView);
		Scene scene = new Scene(hbox);
		stage.setScene(scene);
		stage.setMinHeight(200);
		stage.setMinWidth(200);
		return stage;
	}
}
