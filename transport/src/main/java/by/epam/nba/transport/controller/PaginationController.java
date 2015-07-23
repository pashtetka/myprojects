package by.epam.nba.transport.controller;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ThreadLocalRandom;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import by.epam.nba.transport.bean.ItemsBean;
import by.epam.nba.transport.dao.impl.ItemDaoImpl;
import by.epam.nba.transport.manager.ManagerFactory;
import by.epam.nba.transport.thread.ThreadRunnable;

@ManagedBean(name = "paginationController")
@SessionScoped
public class PaginationController implements Serializable {

	private static final long serialVersionUID = -8849194344896634749L;

	private ItemsBean itemsBean;

	private final Long viewControllerId = ThreadLocalRandom.current()
			.nextLong();

	@PostConstruct
	public void init() {
		itemsBean = new ItemsBean();

	}

	public String visitDirAndSave() {
		ManagerFactory managerFactory = ManagerFactory.getInstance();
		ItemDaoImpl itemDao = new ItemDaoImpl(managerFactory.getEntityManager());
		Path path = Paths.get(itemsBean.getPaths());
		Thread thread = new Thread(new ThreadRunnable(path));
		thread.start();		
		try {
			waitThreadClose(thread);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		itemsBean.setBeans(itemDao.findAll());
		itemDao.getEntityManager().close();
		return "welcome";
	}
	
	protected void waitThreadClose(Thread thread) throws InterruptedException {	
		if(thread.isAlive()){
			thread.join();
		}
		
	}

	public ItemsBean getItemsBean() {
		return itemsBean;
	}

	public void setItemsBean(ItemsBean itemsBean) {
		this.itemsBean = itemsBean;
	}

	public Long getViewControllerId() {
		return viewControllerId;
	}
}
