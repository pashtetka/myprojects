package by.grsu.publication.service;

import java.util.HashMap;
import java.util.Map;

import by.grsu.publication.dao.DaoManager;
import by.grsu.publication.service.impl.CommentServiceImpl;
import by.grsu.publication.service.impl.PeriodicalServiceImpl;
import by.grsu.publication.service.impl.SubscribeServiceImpl;
import by.grsu.publication.service.impl.UserServiceImpl;

public class ServiceManager {

	private DaoManager daoManager;

	@SuppressWarnings("rawtypes")
	private Map<Class, Object> serviceObjects;

	public ServiceManager() {
		this.init();
	}

	public DaoManager getDaoManager() {
		return daoManager;
	}

	public static ServiceManager getDefault() {
		return new ServiceManager();
	}

	@SuppressWarnings("rawtypes")
	private void init() {
		daoManager = DaoManager.getDefault();
		serviceObjects = new HashMap<Class, Object>();
		serviceObjects.put(PeriodicalService.class, new PeriodicalServiceImpl(
				getDaoManager()));
		serviceObjects.put(SubscribeService.class, new SubscribeServiceImpl(
				getDaoManager()));
		serviceObjects.put(UserService.class, new UserServiceImpl(
				getDaoManager()));
		serviceObjects.put(CommentService.class, new CommentServiceImpl(
				getDaoManager()));
	}

	public PeriodicalService getPeriodicalService() {
		return (PeriodicalService) serviceObjects.get(PeriodicalService.class);
	}

	public SubscribeService getSubscribeService() {
		return (SubscribeService) serviceObjects.get(SubscribeService.class);
	}

	public UserService getUserService() {
		return (UserService) serviceObjects.get(UserService.class);
	}
	
	public CommentService getCommentService() {
		return (CommentService) serviceObjects.get(CommentService.class);
	}

}
