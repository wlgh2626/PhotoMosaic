package com.project.photomosaic.image.base;

import java.lang.RuntimeException;
/**
 * Converts between different color models.
 * Only supports 8 bit color depth
 */
public class ColorModel {
	public static final Double[][] RGB_YUV_MATRIX = {
			{0.299, 0.587 , 0.114},
			{-0.14713, -0.28887, 0.436},
			{0.615, -0.51499 , -0.10001}
	};
	
	public static final Double[][] YUV_RGB_MATRIX = {
			{1.0 , 0.0 , 1.13983},
			{1.0 , -0.39465 , -0.58060},
			{1.0 , 2.03211 , 0.0}
	};
	
	public static int RGBToYUV(int RGB) {
		return RGBToYUV( (RGB >> 16) & 0xFF , (RGB >> 8) & 0xFF , RGB & 0xFF);
	}
	
	public static int YUVToRGB(int YUV) {
		return YUVToRGB( (YUV >> 16) & 0xFF , (YUV >> 8) & 0xFF , YUV & 0xFF);
	}
	
	public static int RGBToYUV(int r , int g , int b) {
		Double[] yuv = new Double[3];
		for(int i = 0 ; i < 3 ; i ++) {
			yuv[i] = RGB_YUV_MATRIX[i][0] * r + RGB_YUV_MATRIX[i][1] * g + RGB_YUV_MATRIX[i][2] * b;
			if(yuv[i] > 255) throw new RuntimeException("YUV exceeds 8-bit depth.");
		}
		return yuv[0].intValue() << 16 + yuv[1].intValue() << 8 + yuv[2].intValue();	//returns YUV value with 8 bit depth each
	}
	
	public static int YUVToRGB(int y , int u , int v) {
		Double[] rgb = new Double[3];
		for(int i = 0 ; i < 3 ; i ++) {
			rgb[i] = YUV_RGB_MATRIX[i][0] * y + YUV_RGB_MATRIX[i][1] * u + YUV_RGB_MATRIX[i][2] * v;
			if(rgb[i] > 255) throw new RuntimeException("RGB exceeds 8-bit depth.");
		}
		return rgb[0].intValue() << 16 + rgb[1].intValue() << 8 + rgb[2].intValue();
	}
	
}
