package com.project.photomosaic.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import com.project.photomosaic.image.model.photomosaic.Photomosaic;
import com.project.photomosaic.image.model.photomosaic.sample.SampleIO;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ImageDisplay {
	public static File DUCK = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/duck.jpg");
	public static File LENA = new File(Photomosaic.ORIGINAL_DEFAULT_PATH + "/test/lena.tif");
	public static File SAMPLE = new File(SampleIO.SAMPLE_DEFAULT_PATH + "/test");
	
	private ImageView imageView;

	public ImageDisplay(BufferedImage bufferedImage) {
		Image image = toFXImage(bufferedImage);
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
	
	private static Image toFXImage(BufferedImage image) {
		WritableImage wr = null;
	    if (image != null) {
	        wr = new WritableImage(image.getWidth(), image.getHeight());
	        PixelWriter pw = wr.getPixelWriter();
	        for (int x = 0; x < image.getWidth(); x++) {
	            for (int y = 0; y < image.getHeight(); y++) {
	                pw.setArgb(x, y, image.getRGB(x, y));
	            }
	        }
	    }
	    return new ImageView(wr).getImage();
	}
}
