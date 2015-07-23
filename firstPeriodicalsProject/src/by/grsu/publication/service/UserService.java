package by.grsu.publication.service;

import java.util.List;

import by.grsu.publication.model.IUser;

public interface UserService {

	public IUser getUser(final String login);

	public void getUserNew(final String userName, final String userSurname,
			final String login, final String password, final String mail);

	public List<IUser> getAllUsers();

	public IUser getUserById(final int id);
	
	public void getUpdateUserRole(final int userId, final String userRole);
	
	public List<IUser> getUsersByLogin(final String login);
}
