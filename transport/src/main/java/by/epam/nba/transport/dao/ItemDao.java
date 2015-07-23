package by.epam.nba.transport.dao;

import java.util.List;

import by.epam.nba.transport.bean.Item;

public interface ItemDao {
	
	List<Item> findAll();
	
	void save(Item item);
	
	void saveAll(List<Item> items);

}
