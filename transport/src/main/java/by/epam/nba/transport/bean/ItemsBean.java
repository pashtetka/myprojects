package by.epam.nba.transport.bean;

import java.io.Serializable;
import java.util.List;

import by.epam.nba.transport.bean.constants.Constants;

public class ItemsBean extends Constants implements Serializable {

	private static final long serialVersionUID = 1L;

	// private static final String PATH = "D:/old/фотки";

	private String paths;
	private List<Item> beans;
	private int firstItem;
	private int page;
	private int lastPage;
	private int rows = ROWS;

	public void changePagePlus() throws InterruptedException {
		Thread.sleep(1000);
		int i = page + 1;
		setPage(i);
	}

	public void changePageMinus() throws InterruptedException {
		Thread.sleep(1000);
		int i = page - 1;
		setPage(i);
	}

	public void changeFirstPage() throws InterruptedException {
		Thread.sleep(1000);
		setPage(0);
	}

	public void changeLastPage() throws InterruptedException {
		Thread.sleep(1000);
		setPage(lastPage);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getFirstItem() {
		firstItem = rows * page;
		return firstItem;
	}

	public int getLastPage() {
		lastPage = (int) Math.ceil((float) beans.size() / rows) - 1;
		return lastPage;
	}

	public List<Item> getBeans() {
		return beans;
	}

	public void setBeans(List<Item> beans) {
		this.beans = beans;
	}

	public String getPaths() {
		return paths;
	}

	public void setPaths(String paths) {
		this.paths = paths;
	}

	public int getRows() {
		return rows;
	}

}
