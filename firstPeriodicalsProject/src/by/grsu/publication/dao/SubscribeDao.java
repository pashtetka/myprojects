package by.grsu.publication.dao;

import java.util.List;

import by.grsu.publication.entity.SubscribeEntity;

public interface SubscribeDao {

	public void getCartNew(int periodicalId, int userId);

	public List<SubscribeEntity> getAllSubscribe();

	public List<SubscribeEntity> getUserBasket(int id, String status);

	public void getDeleteBasketById(int userId, int periodicalId);

	public void getDeleteSubscribeById(int periodicalId);
	
	public List<SubscribeEntity> getUserSubscribe(int id);
	
	public void getNewSubscribe(int periodicalId, int userId);
}
