package com.project.photomosaic.image.model.photomosaic.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Reads Files and produces BufferedImage in parallel
 * 
 * @author Jiho
 */
public class ImageIOFactory {
	public static final int DEFAULT_THREAD_COUNT = 4;
	private ImageIORunnable[] runnables;
	private int numThreads;

	public ImageIOFactory() {
		this.numThreads = DEFAULT_THREAD_COUNT;
	}

	public ImageIOFactory(int numThreads) {
		this.numThreads = numThreads;
	}

	/**
	 * 
	 * @param files image files to turn in to BufferedImage
	 * @return ArrayList of BufferedImage from files
	 * @throws InterruptedException
	 */
	public ArrayList<BufferedImage> asBufferedImages(ArrayList<File> files) throws InterruptedException {
		// Initialize Runnables
		runnables = new ImageIORunnable[numThreads];
		for (int i = 0; i < numThreads; i++) {
			runnables[i] = new ImageIORunnable();
		}

		// assign images based on image sizes
		for (File file : files) {
			int smallestIndex = 0;
			for (int i = 0; i < runnables.length; i++) {
				if (runnables[i].getTaskSize() < runnables[smallestIndex].getTaskSize()) {
					smallestIndex = i;
				}
			}
			runnables[smallestIndex].add(file);
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
		private ArrayList<File> files = new ArrayList<File>();
		private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();

		public long getTaskSize() {
			return taskSize;
		}

		public void add(File file) {
			files.add(file);
			taskSize += (long) file.length();
		}

		public ArrayList<BufferedImage> getImages() {
			return images;
		}

		@Override
		public void run() {

			for (File file : files) {
				try {
					images.add(ImageIO.read(file));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}
}
