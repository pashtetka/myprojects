package com.nba.sprhib.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nba.sprhib.dao.DefaultDao;
import com.nba.sprhib.dao.TwitDao;
import com.nba.sprhib.entity.TwitEntity;
import com.nba.sprhib.exceptions.NullResultException;

@Repository
public class TwitDaoImpl extends DefaultDao implements TwitDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<TwitEntity> getAllTwits() {
		Session session = sessionFactory.openSession();
		List<TwitEntity> twits = session.createCriteria(TwitEntity.class).list();
		session.close();
		return twits;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TwitEntity> getByUserId(Long userId) throws NullResultException {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from TwitEntity where user_id = :userId").setLong("userId", userId);
		List<TwitEntity> list = query.list();
		session.close();
		if(list.isEmpty()) {
			throw new NullResultException();
		}
		return list;
	}

}
