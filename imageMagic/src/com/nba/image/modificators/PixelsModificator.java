package com.nba.image.modificators;

import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import com.nba.image.enums.SmoothingColor;

public class PixelsModificator {

	public int getAlpha(int rgb) {
		return (rgb >> 24) & 0xff;
	}

	public int getRed(int rgb) {
		return (rgb >> 16) & 0xff;
	}

	public int getGreen(int rgb) {
		return (rgb >> 8) & 0xff;
	}

	public int getBlue(int rgb) {
		return (rgb) & 0xff;
	}

	public int getRGB(int alpha, int red, int green, int blue) {
		int rgb = 0xffffffff;
		rgb = (rgb | alpha) << 8;
		rgb = (rgb | red) << 8;
		rgb = (rgb | green) << 8;
		rgb = (rgb | blue);
		return rgb;
	}

	public int invert(int alpha, int red, int green, int blue) {
		int rgb = 0xffffffff;
		rgb = (rgb | alpha) << 8;
		int invRed = 255 - red;
		rgb = (rgb | invRed) << 8;
		int invGreen = 255 - green;
		rgb = (rgb | invGreen) << 8;
		int invBlue = 255 - blue;
		rgb = (rgb | invBlue);
		return rgb;
	}

	public int smoothing(int x, int y, BufferedImage image)
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		int red = smoothingColor(x, y, image, SmoothingColor.RED);
		int green = smoothingColor(x, y, image, SmoothingColor.GREEN);
		int blue = smoothingColor(x, y, image, SmoothingColor.BLUE);
		int rgb = getRGB(255, red, green, blue);
		return rgb;
	}

	public int randomPix(int x, int y) {
		Random random = new Random();
		int alpha = 255;
		int red = random.nextInt(256);
		int green = random.nextInt(256);
		int blue = random.nextInt(256);
		int rgb = getRGB(alpha, red, green, blue);
		return rgb;
	}

	private int smoothingColor(int x, int y, BufferedImage image,
			SmoothingColor color) throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		int flag = 1;
		int smLine = smoothingLineColor(x, y, image, color);
		if (x > 0) {
			smLine = smLine + smoothingLineColor(x - 1, y, image, color);
			flag++;
		}
		if (x < image.getWidth() - 1) {
			smLine = smLine + smoothingLineColor(x + 1, y, image, color);
			flag++;
		}
		return smLine / flag;
	}

	@SuppressWarnings("rawtypes")
	private int smoothingLineColor(int x, int y, BufferedImage image,
			SmoothingColor color) throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		int flag = 1;
		String stColor = new String();
		if (SmoothingColor.RED.equals(color)) {
			stColor = "Red";
		}
		if (SmoothingColor.GREEN.equals(color)) {
			stColor = "Green";
		}
		if (SmoothingColor.BLUE.equals(color)) {
			stColor = "Blue";
		}
		StringBuilder method = new StringBuilder();
		method.append("get");
		method.append(stColor);

		Class[] classes = new Class[1];
		classes[0] = int.class;
		Method m = this.getClass().getMethod(method.toString(), classes);

		int sumCol = (int) m.invoke(this, image.getRGB(x, y));
		if (y > 0) {
			sumCol = sumCol + (int) m.invoke(this, image.getRGB(x, y-1));
			flag++;
		}
		if (y < image.getHeight() - 1) {
			sumCol = sumCol + (int) m.invoke(this, image.getRGB(x, y+1));
			flag++;
		}
		int ret = sumCol / flag;
		return ret;
	}
}
