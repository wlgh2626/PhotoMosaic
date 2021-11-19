package com.project.photomosaic.image.model.photomosaic.sample;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SampleFactory{
	public static final int DEFAULT_THREAD_COUNT = 1;
	private SampleRunnable[] runnables;
	private ArrayList<BufferedImage> images;
	
	public SampleFactory(ArrayList<BufferedImage> images) {
		this.images = images;
	}
	
	public ArrayList<Sample> generateSamples() throws InterruptedException {
		// Initialize Runnables
		runnables = new SampleRunnable[DEFAULT_THREAD_COUNT];
		for(int i = 0 ; i < DEFAULT_THREAD_COUNT ; i++) {
			runnables[i] = new SampleRunnable();
		}
		
		// assign images based on image sizes
		for(BufferedImage image: images) {
			int smallestIndex = 0;
			for(int i = 0 ; i < runnables.length ; i++) {
				if(runnables[i].getTaskSize() < runnables[smallestIndex].getTaskSize()) {
					smallestIndex = i;
				}
			}
			runnables[smallestIndex].add(image);
		}
		
		// run the threads to generate samples
		//-----------------------------------
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(SampleRunnable runnable: runnables) {
			Thread t = new Thread(runnable);
			t.run();
			threads.add(t);
		}
		
		for(Thread thread : threads) {
			thread.join();
		}
		//-----------------------------------
		
		ArrayList<Sample> samples = new ArrayList<Sample>();
		for(SampleRunnable runnable: runnables) {
			samples.addAll(runnable.getSamples());
		}
		return samples;
	}
	
	private class SampleRunnable implements Runnable{
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
			for(BufferedImage image : images) {
				samples.add(new Sample(image));
			}
		}
		
	}
	
}