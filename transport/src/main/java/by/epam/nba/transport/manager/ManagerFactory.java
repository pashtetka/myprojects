package by.epam.nba.transport.manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerFactory {

	private static ManagerFactory instance;
	private static EntityManagerFactory entityManagerFactory;

	public EntityManagerFactory createEntityManagerFactory() {
		if (entityManagerFactory == null || !entityManagerFactory.isOpen()) {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("transportConnect");
		}
		return entityManagerFactory;
	}

	/**
	 * @return the entityManagerFactory
	 */
	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	public static ManagerFactory getInstance() {
		if (instance == null) {
			instance = new ManagerFactory();
		}
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence
					.createEntityManagerFactory("transportConnect");
		}
		return instance;
	}

}
