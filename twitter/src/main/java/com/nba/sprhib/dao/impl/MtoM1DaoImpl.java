package com.nba.sprhib.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nba.sprhib.dao.DefaultDao;
import com.nba.sprhib.dao.MtoM1Dao;
import com.nba.sprhib.entity.MtoM1;

@Repository
public class MtoM1DaoImpl extends DefaultDao implements MtoM1Dao {

	@Override
	public List<MtoM1> getAllMtoM1() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<MtoM1> list = session.createCriteria(MtoM1.class).list();
		session.close();
		return list;
	}

}
