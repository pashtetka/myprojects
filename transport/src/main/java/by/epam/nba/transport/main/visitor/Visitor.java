package by.epam.nba.transport.main.visitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import by.epam.nba.transport.main.ItemList;
import by.epam.nba.transport.main.entity.Item;

public class Visitor extends SimpleFileVisitor<Path> {
	
	private ItemList itemList;
	
	public Visitor(ItemList itemList) {
		this.itemList = itemList;
	}
	
	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attrs)
			throws IOException {
		
		File file = path.toFile();
		Item item = new Item();
		item.setName(file.getName());
		item.setPath(path);
		item.setSize(file.length());
		item.setCurrentTime(System.currentTimeMillis());
		itemList.add(item);
		System.out.println("SAVE: " + item.getPath() + ". New size: " + itemList.getItems().size());		
		try {
			int random = 1 + (int)(Math.random() * ((30 - 1) + 1));
			Thread.sleep(random*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return FileVisitResult.CONTINUE;
	}

}
