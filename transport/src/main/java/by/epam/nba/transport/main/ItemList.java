package by.epam.nba.transport.main;

import java.util.List;
import java.util.Vector;

import by.epam.nba.transport.main.entity.Item;

public class ItemList {
	
	private List<Item> items;
	
	public ItemList() {
		items = new Vector<Item>();
	}
	
	public void add(Item item) {
		items.add(item);
	}
	
	public void remove(Item item) {
		items.remove(item);
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
