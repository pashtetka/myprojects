package by.grsu.publication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.grsu.publication.dao.UserDao;
import by.grsu.publication.entity.UserEntity;

public class UserDaoImpl implements UserDao {

	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	private static final String LOGIN_USER = "SELECT `users`.`id`, `users`.`login`, `users`.`password`, `users`.`e-mail`, `users`.`user_Type`, `users`.`name`, `users`.`surname` FROM `periodicals_project`.`users` where `users`.`login` = ? ";
	private static final String NEW_USER = "INSERT INTO `periodicals_project`.`users` (`login`, `password`, `e-mail`, `user_Type`, `name`, `surname`) VALUES (?, ?, ?, 'user', ?, ?)";
	private static final String ALL_USERS = "SELECT `users`.`id`, `users`.`login`, `users`.`user_Type` FROM `periodicals_project`.`users`";
	private static final String USER_BY_ID = "SELECT `users`.`id`, `users`.`login`, `users`.`password`, `users`.`e-mail`, `users`.`user_Type`, `users`.`name`, `users`.`surname` FROM `periodicals_project`.`users` where `users`.`id` = ?";
	private static final String UPDATE_USER_ROLE = "UPDATE `periodicals_project`.`users` SET `user_Type`= ? WHERE `id`= ?";
	private static final String USERS_BY_LOGIN = "SELECT `users`.`id`, `users`.`login`, `users`.`password`, `users`.`e-mail`, `users`.`user_Type`, `users`.`name`, `users`.`surname` FROM `periodicals_project`.`users` WHERE `users`.`login` REGEXP ? ";

	private Connection connection;

	public UserDaoImpl(Connection conn) {
		this.connection = conn;
	}

	@Override
	public UserEntity getUser(String login) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserEntity user = null;
		try {
			stmt = connection.prepareStatement(UserDaoImpl.LOGIN_USER);
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserEntity(rs.getInt("id"), rs.getString("login"),
						rs.getString("password"), rs.getString("e-mail"),
						rs.getString("user_Type"), rs.getString("name"),
						rs.getString("surname"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					logger.error(DaoErrors.RS_ERROR + e.getMessage());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					logger.error(DaoErrors.STMT_ERROR + e.getMessage());
				}
			}
		}
		return user;
	}

	@Override
	public void getUserNew(String userName, String userSurname, String login,
			String password, String mail) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(UserDaoImpl.NEW_USER);
			stmt.setString(1, login);
			stmt.setString(2, password);
			stmt.setString(3, mail);
			stmt.setString(4, userName);
			stmt.setString(5, userSurname);
			stmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					logger.error(DaoErrors.STMT_ERROR + e.getMessage());
				}
			}
		}
	}

	@Override
	public List<UserEntity> getAllUsers() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserEntity> users = new ArrayList<UserEntity>();
		try {
			stmt = connection.prepareStatement(UserDaoImpl.ALL_USERS);
			rs = stmt.executeQuery();
			while (rs.next()) {
				users.add(new UserEntity(rs.getInt("id"),
						rs.getString("login"), rs.getString("user_Type")));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					logger.error(DaoErrors.RS_ERROR + e.getMessage());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					logger.error(DaoErrors.STMT_ERROR + e.getMessage());
				}
			}
		}
		return users;
	}

	@Override
	public UserEntity getUserById(int id) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		UserEntity user = null;
		try {
			stmt = connection.prepareStatement(UserDaoImpl.USER_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				user = new UserEntity(rs.getInt("id"), rs.getString("login"),
						rs.getString("password"), rs.getString("e-mail"),
						rs.getString("user_Type"), rs.getString("name"),
						rs.getString("surname"));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					logger.error(DaoErrors.RS_ERROR + e.getMessage());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					logger.error(DaoErrors.STMT_ERROR + e.getMessage());
				}
			}
		}
		return user;
	}

	@Override
	public void getUpdateUserRole(int userId, String userRole) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(UserDaoImpl.UPDATE_USER_ROLE);
			stmt.setString(1, userRole);
			stmt.setInt(2, userId);
			stmt.executeUpdate();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					logger.error(DaoErrors.STMT_ERROR + e.getMessage());
				}
			}
		}
	}

	@Override
	public List<UserEntity> getUsersByLogin(String login) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<UserEntity> usersEnt = new ArrayList<UserEntity>();
		try {
			stmt = connection.prepareStatement(UserDaoImpl.USERS_BY_LOGIN);
			stmt.setString(1, "^" + login);
			rs = stmt.executeQuery();
			while (rs.next()) {
				usersEnt.add(new UserEntity(rs.getInt("id"), rs
						.getString("login"), rs.getString("password"), rs
						.getString("e-mail"), rs.getString("user_Type"), rs
						.getString("name"), rs.getString("surname")));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					logger.error(DaoErrors.RS_ERROR + e.getMessage());
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					logger.error(DaoErrors.STMT_ERROR + e.getMessage());
				}
			}
		}
		return usersEnt;
	}
}
