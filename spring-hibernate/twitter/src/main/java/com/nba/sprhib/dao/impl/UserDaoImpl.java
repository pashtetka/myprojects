package com.nba.sprhib.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.nba.sprhib.dao.DefaultDao;
import com.nba.sprhib.dao.UserDao;
import com.nba.sprhib.entity.UserEntity;

@Repository(value = "userDao")
public class UserDaoImpl extends DefaultDao implements UserDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> getAllUsers() {
		Session session = sessionFactory.openSession();
		List<UserEntity> users = session.createCriteria(UserEntity.class).list();
		session.close();
		return users;
	}

	
	
}
