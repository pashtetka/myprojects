package by.epam.nba.transport.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.nba.transport.bean.Item;
import by.epam.nba.transport.dao.ItemDao;

public class ItemDaoImpl implements ItemDao {
	
	private static final Logger LOG = LogManager.getLogger(ItemDaoImpl.class);

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Item> findAll() {
		Query query = entityManager.createQuery("SELECT it FROM Item it");
		return query.getResultList();
	}

	@Override
	public void save(Item item) {
		entityManager.getTransaction().begin();
		try {
			entityManager.persist(item);
			entityManager.getTransaction().commit();
			LOG.info("Commit!");
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOG.error("Rollback!");
		}
	}

	@Override
	public void saveAll(List<Item> items) {
		entityManager.getTransaction().begin();
		try {
			for (Item item : items) {
				entityManager.persist(item);
			}
			entityManager.getTransaction().commit();
			LOG.info("Commit!");
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOG.error("Rollback!");
		}
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ItemDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
