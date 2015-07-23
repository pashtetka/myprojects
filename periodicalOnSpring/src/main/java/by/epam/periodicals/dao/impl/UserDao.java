package by.epam.periodicals.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.epam.periodicals.model.User;

@Repository
public class UserDao extends AbstractDao<User>{
	
	public List<User> getAllUsers() {
		return getAll();
	}
	
}
