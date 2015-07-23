package com.nba.image;

import com.nba.image.constants.Constants;
import com.nba.image.modificators.ImageModificator;


public class Main {

	public static void main(String[] args) {
		
		ImageModificator modificator = new ImageModificator();
		
		modificator.createRandomImage(Constants.RANDOM_PIXELS_PATH, 500, 500);
		modificator.invertImage(Constants.MAIN_FILE, Constants.INVERT_PATH);
		modificator.smoothingImage(Constants.MAIN_FILE, Constants.SMOOTHING_IMAGE_PATH);
		modificator.smoothingImage(Constants.SMOOTHING_IMAGE_PATH, Constants.SMOOTHING_SECOND_PATH);
		
	}	
}
