package by.epam.periodicals.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.periodicals.model.User;
import by.epam.periodicals.repository.UserRepository;
import by.epam.periodicals.services.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;

//	@Override
//	@Transactional(readOnly = true)
//	public List<User> findAll() {
//		List<User> users = getEntityManager().createNamedQuery("User.findAll", User.class).getResultList();
//		return users;
//	}
	
//	@Override
//	@Transactional(readOnly = true)
//	public User findUserById(Long id) {
//		TypedQuery<User> query = getEntityManager().createNamedQuery("User.findUserById", User.class);
//		query.setParameter("id", id);
//		return query.getSingleResult();
//	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		Iterable<User> it = userRepository.findAll();
		List<User> list = new ArrayList<User>();
		for (User user : it) {
			list.add(user);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserById(Long id) {
		return userRepository.findOne(id);
	}
	
	@Override
	public void save(User user) {
		userRepository.save(user);		
	}

	@Override
	@Transactional
	public void update(User user) {
		userRepository.save(user);		
	}

}
