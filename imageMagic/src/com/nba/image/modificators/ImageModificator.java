package com.nba.image.modificators;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.imageio.ImageIO;

public class ImageModificator {

	private PixelsModificator pixelsModificator;

	public ImageModificator() {
		pixelsModificator = new PixelsModificator();
	}

	public void createRandomImage(String path, int width, int height) {
		File file = new File(path);
		BufferedImage bImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rndRGB = pixelsModificator.randomPix(i, j);
				bImage.setRGB(i, j, rndRGB);
			}
		}
		try {
			ImageIO.write(bImage, "jpg", file);
		} catch (IOException e) {
			System.out.println("ERROR: Can not write image!");
		}
	}

	public void invertImage(String mainImagePath, String invertImagePath) {
		File maintImage = new File(mainImagePath);
		File invertImage = new File(invertImagePath);

		try {
			BufferedImage mainBI = ImageIO.read(maintImage);
			for (int i = 0; i < mainBI.getWidth(); i++) {
				for (int j = 0; j < mainBI.getHeight(); j++) {
					int rgbPix = mainBI.getRGB(i, j);
					int invRgbPix = pixelsModificator.invert(
							pixelsModificator.getAlpha(rgbPix),
							pixelsModificator.getRed(rgbPix),
							pixelsModificator.getGreen(rgbPix),
							pixelsModificator.getBlue(rgbPix));
					mainBI.setRGB(i, j, invRgbPix);
				}
			}
			ImageIO.write(mainBI, "jpg", invertImage);
		} catch (IOException e) {
			System.out.println("ERROR: Error occurs during reading!");
		}

	}
	
	public void smoothingImage(String mainImagePath, String smoothingImagePath) {
		File maintImage = new File(mainImagePath);
		File smoothingImage = new File(smoothingImagePath);
		
		BufferedImage mainBI;
		try {
			mainBI = ImageIO.read(maintImage);
			for (int i = 0; i < mainBI.getWidth(); i++) {
				for (int j = 0; j < mainBI.getHeight(); j++) {
					int smPix = pixelsModificator.smoothing(i, j, mainBI);
					mainBI.setRGB(i, j, smPix);
				}
			}
			ImageIO.write(mainBI, "jpg", smoothingImage);
		} catch (IOException e) {
			System.out.println("ERROR: Error occurs during reading or writing!");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("ERROR: Any parameter is null!");
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

}
