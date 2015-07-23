package by.epam.periodicals.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<T> {
	
	private Class<T> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getCriteria().list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getByQuery(Query query) {
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public T get(Long id) {
		return (T) getCurrentSession().get(entityClass, id);
	}
	
	public void remove(Long id) {
		getCurrentSession().delete(get(id));
	}
	
	public void create(T obj) {
		getCurrentSession().persist(obj);
	}
	
	public void update(T obj) {
		getCurrentSession().update(obj);
	}

	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	protected Criteria getCriteria() {
		return getCurrentSession().createCriteria(entityClass);
	}

}
