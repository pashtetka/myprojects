package by.grsu.publication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.grsu.publication.dao.SubscribeDao;
import by.grsu.publication.entity.SubscribeEntity;

public class SubscribeDaoImpl implements SubscribeDao {

	private static final Logger logger = Logger
			.getLogger(SubscribeDaoImpl.class);

	private static final String ALL_SUBSCRIBE = "SELECT `subscription`.`id`, `subscription`.`periodical_id`, `subscription`.`user_id`, `subscription`.`status_Of_The_Publication` FROM `periodicals_project`.`subscription`";
	private static final String NEW_CART = "INSERT INTO `periodicals_project`.`subscription` (`periodical_id`, `user_id`, `status_Of_The_Publication`) VALUES (?, ?, ?)";
	private static final String USER_BASKET = "SELECT `subscription`.`id`, `subscription`.`periodical_id`, `subscription`.`user_id`, `subscription`.`status_Of_The_Publication` FROM `periodicals_project`.`subscription` where `subscription`.`user_id` = ? and `subscription`.`status_Of_The_Publication` = ? ";
	private static final String USER_SUB = "SELECT `subscription`.`id`, `subscription`.`periodical_id`, `subscription`.`user_id`, `subscription`.`status_Of_The_Publication` FROM `periodicals_project`.`subscription` where `subscription`.`user_id` = ? ";
	private static final String DELETE_BASKET_BY_ID = "DELETE FROM `periodicals_project`.`subscription` WHERE  `user_id` = ? and `periodical_id`= ? ";
	private static final String DELETE_SUBSCRIBE_BY_ID = "DELETE FROM `periodicals_project`.`subscription` WHERE `periodical_id`= ? ";
	private static final String NEW_SUBSCRIBR = "UPDATE `periodicals_project`.`subscription` SET `status_Of_The_Publication`= ? WHERE `periodical_id`= ? and `user_id` = ? ";

	private Connection connection;

	public SubscribeDaoImpl(Connection conn) {
		this.connection = conn;
	}

	@Override
	public void getCartNew(int periodicalId, int userId) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SubscribeDaoImpl.NEW_CART);
			stmt.setInt(1, periodicalId);
			stmt.setInt(2, userId);
			stmt.setString(3, "basket");
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
	public List<SubscribeEntity> getAllSubscribe() {
		List<SubscribeEntity> subscribe = new ArrayList<SubscribeEntity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(SubscribeDaoImpl.ALL_SUBSCRIBE);
			rs = stmt.executeQuery();
			while (rs.next()) {
				subscribe.add(new SubscribeEntity(rs.getInt("id"), rs
						.getInt("periodical_id"), rs.getInt("user_id"), rs
						.getString("status_Of_The_Publication")));
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
		return subscribe;
	}

	@Override
	public List<SubscribeEntity> getUserBasket(int id, String status) {
		List<SubscribeEntity> subscribe = new ArrayList<SubscribeEntity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(SubscribeDaoImpl.USER_BASKET);
			stmt.setInt(1, id);
			stmt.setString(2, status);
			rs = stmt.executeQuery();
			while (rs.next()) {
				subscribe.add(new SubscribeEntity(rs.getInt("id"), rs
						.getInt("periodical_id"), rs.getInt("user_id"), rs
						.getString("status_Of_The_Publication")));
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
		return subscribe;
	}

	@Override
	public void getDeleteBasketById(int userId, int periodicalId) {
		PreparedStatement stmt = null;
		try {
			stmt = connection
					.prepareStatement(SubscribeDaoImpl.DELETE_BASKET_BY_ID);
			stmt.setInt(1, userId);
			stmt.setInt(2, periodicalId);
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
	public void getDeleteSubscribeById(int periodicalId) {
		PreparedStatement stmt = null;
		try {
			stmt = connection
					.prepareStatement(SubscribeDaoImpl.DELETE_SUBSCRIBE_BY_ID);
			stmt.setInt(1, periodicalId);
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
	public List<SubscribeEntity> getUserSubscribe(int id) {
		List<SubscribeEntity> subscribe = new ArrayList<SubscribeEntity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement(SubscribeDaoImpl.USER_SUB);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				subscribe.add(new SubscribeEntity(rs.getInt("id"), rs
						.getInt("periodical_id"), rs.getInt("user_id"), rs
						.getString("status_Of_The_Publication")));
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
		return subscribe;
	}

	@Override
	public void getNewSubscribe(int periodicalId, int userId) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(SubscribeDaoImpl.NEW_SUBSCRIBR);
			stmt.setString(1, "subscribe");
			stmt.setInt(2, periodicalId);
			stmt.setInt(3, userId);
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
}
