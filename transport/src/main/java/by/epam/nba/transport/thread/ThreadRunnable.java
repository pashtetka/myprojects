package by.epam.nba.transport.thread;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import by.epam.nba.transport.bean.Item;
import by.epam.nba.transport.dao.ItemDao;
import by.epam.nba.transport.dao.impl.ItemDaoImpl;
import by.epam.nba.transport.manager.ManagerFactory;
import by.epam.nba.transport.visitor.DefaultFileVisitor;

public class ThreadRunnable implements Runnable {

	private Path path;
	private List<Item> items;
	private ManagerFactory managerFactory;
	private DefaultFileVisitor visitor;

	public ThreadRunnable(Path path) {
		this.path = path;
		this.managerFactory = ManagerFactory.getInstance();
	}

	@Override
	public void run() {
		visitor = new DefaultFileVisitor(path.toString());
		try {
			Files.walkFileTree(path, visitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		items = visitor.getItems();
		ItemDao itemDao = new ItemDaoImpl(managerFactory.getEntityManager());
		itemDao.saveAll(items);
		try {
			waitThreadClose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	protected void waitThreadClose() throws InterruptedException {
		for (Thread thread : visitor.getThreads()) {
			if(thread.isAlive()){
				thread.join();
			}
		}
	}

}
