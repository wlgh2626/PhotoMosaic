package com.project.photomosaic.image.model.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Reads Files and produces BufferedImage in parallel with fine granularity
 * 
 * @author Jiho
 */
public class ImageThreads {
	public static final int DEFAULT_THREAD_COUNT = 2;
	private ImageIORunnable[] runnables;
	private int numThreads;

	public ImageThreads() {
		this.numThreads = DEFAULT_THREAD_COUNT;
	}

	public ImageThreads(int numThreads) {
		if (numThreads > 0) {
			this.numThreads = numThreads;
		} else {
			this.numThreads = DEFAULT_THREAD_COUNT;
		}
	}

	/**
	 * 
	 * @param fileBytes image file bytes to turn in to BufferedImage
	 * @return ArrayList of BufferedImage from files
	 * @throws InterruptedException
	 */
	public ArrayList<BufferedImage> asBufferedImages(ArrayList<byte[]> fileBytesList) throws InterruptedException {
		// Initialize Runnables
		runnables = new ImageIORunnable[numThreads];
		for (int i = 0; i < numThreads; i++) {
			runnables[i] = new ImageIORunnable();
		}

		// assign images based on image sizes
		for (byte[] fileBytes : fileBytesList) {
			int smallestIndex = 0;
			for (int i = 0; i < runnables.length; i++) {
				if (runnables[i].getTaskSize() < runnables[smallestIndex].getTaskSize()) {
					smallestIndex = i;
				}
			}
			runnables[smallestIndex].add(fileBytes);
		}

		// run the threads to generate samples
		// -----------------------------------
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (ImageIORunnable runnable : runnables) {
			threads.add(new Thread(runnable));
		}

		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
		// -----------------------------------

		ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		for (ImageIORunnable runnable : runnables) {
			images.addAll(runnable.getImages());
		}
		return images;
	}

	private class ImageIORunnable implements Runnable {
		private long taskSize = 0;
		private ArrayList<byte[]> fileBytesList = new ArrayList<byte[]>();
		private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

		public long getTaskSize() {
			return taskSize;
		}

		public void add(byte[] fileBytes) {
			fileBytesList.add(fileBytes);
			taskSize += (long) fileBytes.length;
		}

		public ArrayList<BufferedImage> getImages() {
			return images;
		}

		@Override
		public void run() {

			for (byte[] fileBytes : fileBytesList) {
				try {
					images.add(ImageIO.read(new ByteArrayInputStream(fileBytes)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
