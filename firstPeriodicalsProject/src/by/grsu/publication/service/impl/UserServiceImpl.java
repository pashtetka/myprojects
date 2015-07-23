package by.grsu.publication.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.grsu.publication.dao.DaoManager;
import by.grsu.publication.dao.UserDao;
import by.grsu.publication.dao.base.DaoCommand;
import by.grsu.publication.service.UserService;
import by.grsu.publication.model.IUser;
import by.grsu.publication.dao.base.ConnectionBehavior;
import by.grsu.publication.entity.UserEntity;

public class UserServiceImpl implements UserService {

	private DaoManager daoManager;
	private ServiceConvert conv = new ServiceConvert();

	public UserServiceImpl(DaoManager daoManager) {
		this.daoManager = daoManager;
	}

	public DaoManager getDaoManager() {
		return daoManager;
	}

	@Override
	public IUser getUser(final String login) {
		return (IUser) getDaoManager().transaction(new DaoCommand() {

			public Object execute(final DaoManager manager) {
				IUser user = null;
				final UserDao userDao = manager.getDao(UserDao.class);
				UserEntity userEnt = userDao.getUser(login);
				if (userEnt != null) {
					user = conv.convertUser(userEnt);
				}
				return user;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@Override
	public void getUserNew(final String userName, final String userSurname,
			final String login, final String password, final String mail) {
		getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				final UserDao userDao = daoManager.getDao(UserDao.class);
				userDao.getUserNew(userName, userSurname, login, password, mail);
				return null;
			}
		}, ConnectionBehavior.CLOSE);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IUser> getAllUsers() {
		return (List<IUser>) getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				List<UserEntity> usersEnt = null;
				List<IUser> users = new ArrayList<IUser>();
				final UserDao userDao = daoManager.getDao(UserDao.class);
				usersEnt = userDao.getAllUsers();
				if (!usersEnt.isEmpty()) {
					for (UserEntity usEnt : usersEnt) {
						users.add(conv.convertUser(usEnt));
					}
				}
				return users;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@Override
	public IUser getUserById(final int id) {
		return (IUser) getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				UserEntity userEnt = null;
				IUser user = null;
				final UserDao userDao = daoManager.getDao(UserDao.class);
				userEnt = userDao.getUserById(id);
				if (userEnt != null) {
					user = conv.convertUser(userEnt);
				}
				return user;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@Override
	public void getUpdateUserRole(final int userId, final String userRole) {
		getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				final UserDao userDao = daoManager.getDao(UserDao.class);
				userDao.getUpdateUserRole(userId, userRole);
				return null;
			}
		}, ConnectionBehavior.CLOSE);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IUser> getUsersByLogin(final String login) {
		return (List<IUser>) getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				List<UserEntity> usersEnt = null;
				List<IUser> users = new ArrayList<IUser>();
				final UserDao userDao = daoManager.getDao(UserDao.class);
				usersEnt = userDao.getUsersByLogin(login);
				if (!usersEnt.isEmpty()) {
					for (UserEntity usEnt : usersEnt) {
						users.add(conv.convertUser(usEnt));
					}
				}
				return users;
			}
		}, ConnectionBehavior.CLOSE);
	}

}
