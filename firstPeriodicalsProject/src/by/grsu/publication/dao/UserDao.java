package by.grsu.publication.dao;

import java.util.List;

import by.grsu.publication.entity.UserEntity;

public interface UserDao {

	public UserEntity getUser(String login);

	public void getUserNew(String userName, String userSurname, String login,
			String password, String mail);

	public List<UserEntity> getAllUsers();
	
	public UserEntity getUserById(int id);
	
	public void getUpdateUserRole(int userId, String userRole);
	
	public List<UserEntity> getUsersByLogin(String login);
}
