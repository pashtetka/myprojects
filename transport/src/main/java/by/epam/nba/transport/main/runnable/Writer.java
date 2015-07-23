package by.epam.nba.transport.main.runnable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import by.epam.nba.transport.main.ItemList;
import by.epam.nba.transport.main.entity.ThreadObject;
import by.epam.nba.transport.main.visitor.Visitor;

public class Writer implements Runnable {
	
	private ItemList itemList;
	private Path path;
	private ThreadObject object;
	
	public Writer(ItemList itemList, Path path, ThreadObject object) {
		this.itemList = itemList;
		this.path = path;
		this.object = object;
	}

	@Override
	public void run() {
		Visitor visitor = new Visitor(itemList);
		try {
			Files.walkFileTree(path, visitor);
			object.setWriterIsAlive(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
