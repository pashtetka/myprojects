package by.epam.nba.transport.visitor;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import by.epam.nba.transport.bean.Item;
import by.epam.nba.transport.thread.ThreadRunnable;

public class DefaultFileVisitor extends SimpleFileVisitor<Path> {
	
	private List<Item> items;
	private String rootPath;
	private List<Thread> threads;

	public DefaultFileVisitor(String rootPath) {
		this.rootPath = rootPath;
		items = new ArrayList<Item>();
		threads = new ArrayList<Thread>();
	}
	
	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
			throws IOException {
		if(dir != null && dir.equals(Paths.get(rootPath))){
			return FileVisitResult.CONTINUE;
		}
		Thread thread = new Thread(new ThreadRunnable(dir));
		threads.add(thread);
		thread.start();
		return FileVisitResult.SKIP_SUBTREE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		Item item = new Item();
		File f = file.toFile();
		item.setName(f.getName());
		item.setSize(f.length());
		item.setDescription(file.toString());
		items.add(item);
		return FileVisitResult.CONTINUE;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public List<Thread> getThreads() {
		return threads;
	}

}
