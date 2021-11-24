package com.project.photomosaic.image.model.photomosaic.sample;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SampleThreads {
	public static final int DEFAULT_THREAD_COUNT = 4;
	private SampleRunnable[] runnables;
	private ArrayList<BufferedImage> images;

	public SampleThreads(ArrayList<BufferedImage> images) {
		this.images = images;
	}

	public ArrayList<Sample> asSamples() throws InterruptedException {
		// Initialize Runnables
		runnables = new SampleRunnable[DEFAULT_THREAD_COUNT];
		for (int i = 0; i < DEFAULT_THREAD_COUNT; i++) {
			runnables[i] = new SampleRunnable();
		}

		// assign images based on image sizes
		for (BufferedImage image : images) {
			int smallestIndex = 0;
			for (int i = 0; i < runnables.length; i++) {
				if (runnables[i].getTaskSize() < runnables[smallestIndex].getTaskSize()) {
					smallestIndex = i;
				}
			}
			runnables[smallestIndex].add(image);
		}

		// run the threads to generate samples
		// -----------------------------------
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (SampleRunnable runnable : runnables) {
			threads.add(new Thread(runnable));
		}

		for (Thread thread : threads) {
			thread.start();
		}
		for (Thread thread : threads) {
			thread.join();
		}
		// -----------------------------------

		ArrayList<Sample> samples = new ArrayList<Sample>();
		for (SampleRunnable runnable : runnables) {
			samples.addAll(runnable.getSamples());
		}
		return samples;
	}

	private class SampleRunnable implements Runnable {
		private long taskSize = 0;
		private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
		private ArrayList<Sample> samples = new ArrayList<Sample>();

		public long getTaskSize() {
			return taskSize;
		}

		public void add(BufferedImage image) {
			images.add(image);
			taskSize += (long) image.getWidth() * (long) image.getHeight();
		}

		public ArrayList<Sample> getSamples() {
			return samples;
		}

		@Override
		public void run() {
			for (BufferedImage image : images) {
				samples.add(new Sample(image));
			}
		}

	}

}
