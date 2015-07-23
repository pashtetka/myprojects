package com.nba.sprhib.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nba.sprhib.dao.Class2Dao;
import com.nba.sprhib.dao.DefaultDao;
import com.nba.sprhib.entity.Class2;

@Repository
public class Class2DaoImpl extends DefaultDao implements Class2Dao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Class2> getAllClass2() {
		Session session = sessionFactory.openSession();
		List<Class2> list = session.createCriteria(Class2.class).list();
		session.close();
		return list;
	}

}
