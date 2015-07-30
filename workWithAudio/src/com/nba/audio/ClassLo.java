package com.nba.audio;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ClassLo {
	
	public void sound(String path) {
		try {
		    File soundFile = new File(path); //Звуковой файл
		    System.out.println(soundFile.getName());
		    
		    //Получаем AudioInputStream
		    //Вот тут могут полететь IOException и UnsupportedAudioFileException
		    AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
		    
		    //Получаем реализацию интерфейса Clip
		    //Может выкинуть LineUnavailableException
		    Clip clip = AudioSystem.getClip();
		    
		    //Загружаем наш звуковой поток в Clip
		    //Может выкинуть IOException и LineUnavailableException
		    clip.open(ais);
		    
		    clip.setFramePosition(0); //устанавливаем указатель на старт
		    clip.start(); //Поехали!!!

		    //Если не запущено других потоков, то стоит подождать, пока клип не закончится
		        //В GUI-приложениях следующие 3 строчки не понадобятся
		    Thread.sleep(clip.getMicrosecondLength()/1000);
		    clip.stop(); //Останавливаем
		    clip.close(); //Закрываем
		} catch(IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
		    exc.printStackTrace();
		} catch (InterruptedException exc) {}  
	}
	
	public void video(String path) throws IOException {
		Desktop.getDesktop().open( new File(path) ); 
	}

}
