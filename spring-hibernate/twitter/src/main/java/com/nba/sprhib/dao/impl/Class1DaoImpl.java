package com.nba.sprhib.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nba.sprhib.dao.Class1Dao;
import com.nba.sprhib.dao.DefaultDao;
import com.nba.sprhib.entity.Class1;

@Repository
public class Class1DaoImpl extends DefaultDao implements Class1Dao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Class1> getAllClass1() {
		Session session = sessionFactory.openSession();
		List<Class1> list = session.createCriteria(Class1.class).list();
		session.close();
		return list;
	}

}
