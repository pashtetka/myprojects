package by.grsu.publication.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import by.grsu.publication.dao.CommentDao;
import by.grsu.publication.entity.CommentsEntity;

public class CommentDaoImpl implements CommentDao {

	private static final Logger logger = Logger.getLogger(CommentDaoImpl.class);

	private static final String NEW_COMMENT = "INSERT INTO `periodicals_project`.`comments` (`periodical_id`, `user_id`, `comment`) VALUES (?, ?, ?)";
	private static final String PERIODICAL_COMMENTS = "SELECT `comments`.`id`, `comments`.`periodical_id`, `users`.`login`, `comments`.`comment` FROM `periodicals_project`.`comments`, `periodicals_project`.`users` where `comments`.`user_id`= `users`.`id` and `comments`.`periodical_id` = ? ";
	private static final String DELETE_COMMENTS_BY_PERIODICAL_ID = "DELETE FROM periodicals_project.comments WHERE comments.periodical_id = ? ";
	private static final String DELETE_COMMENT_BY_ID = "DELETE FROM `periodicals_project`.`comments` WHERE `id`= ? ";

	private Connection connection;

	public CommentDaoImpl(Connection conn) {
		this.connection = conn;
	}

	@Override
	public void getNewComment(int periodicalId, int userId, String comm) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(CommentDaoImpl.NEW_COMMENT);
			stmt.setInt(1, periodicalId);
			stmt.setInt(2, userId);
			stmt.setString(3, comm);
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
	public List<CommentsEntity> getPeriodicalComments(int periodicalId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CommentsEntity> comEnt = new ArrayList<CommentsEntity>();
		try {
			stmt = connection
					.prepareStatement(CommentDaoImpl.PERIODICAL_COMMENTS);
			stmt.setInt(1, periodicalId);
			rs = stmt.executeQuery();
			while (rs.next()) {
				comEnt.add(new CommentsEntity(rs.getInt("id"), rs
						.getInt("periodical_id"), rs.getString("login"), rs
						.getString("comment")));
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
		return comEnt;
	}

	@Override
	public void getDeleteComment(int periodicalId) {
		PreparedStatement stmt = null;
		try {
			stmt = connection
					.prepareStatement(CommentDaoImpl.DELETE_COMMENTS_BY_PERIODICAL_ID);
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
	public void getDeleteCommentById(int id) {
		PreparedStatement stmt = null;
		try {
			stmt = connection
					.prepareStatement(CommentDaoImpl.DELETE_COMMENT_BY_ID);
			stmt.setInt(1, id);
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
