package com.nba.sprhib.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nba.sprhib.dao.DefaultDao;
import com.nba.sprhib.dao.MtoM2Dao;
import com.nba.sprhib.entity.MtoM2;

@Repository
public class MtoM2DaoImpl extends DefaultDao implements MtoM2Dao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MtoM2> getAllMtoM2() {
		Session session = sessionFactory.openSession();
		List<MtoM2> list = session.createCriteria(MtoM2.class).list();
		session.close();
		return list;
	}

}
