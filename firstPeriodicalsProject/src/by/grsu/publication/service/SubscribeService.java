package by.grsu.publication.service;

import java.util.List;

import by.grsu.publication.model.Subscribe;

public interface SubscribeService {

	public void getCartNew(final int periodicalId, final int userId);

	public List<Subscribe> getAllSubscribe();

	public void getDeleteBasketById(final int userId, final int periodicalId);
	
	public void getNewSubscribe(final int periodicalId, final int userId);

}
