package by.epam.nba.transport.main;

import java.nio.file.Path;
import java.nio.file.Paths;

import by.epam.nba.transport.main.entity.ThreadObject;
import by.epam.nba.transport.main.runnable.Remover;
import by.epam.nba.transport.main.runnable.Writer;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		Path path = Paths.get("D:/old/photo/20.05.2015");
		long millisecond = 1000;
		
		ItemList itemList = new ItemList();
		ThreadObject object = new ThreadObject();
		
		Thread thread1 = new Thread(new Writer(itemList, path, object));
		Thread thread2 = new Thread(new Remover(itemList, millisecond, object));
		thread1.start();
		thread2.start();
		
		thread1.join();
		thread2.join();
		
		System.out.println("Finished!");
		
	}

}
