package by.epam.periodicals.services;

import java.util.List;

import by.epam.periodicals.model.User;

public interface UserService {
	
	List<User> findAll();
	
	User findUserById(Long id);

	void save(User user);
	
	void update(User user);
}
