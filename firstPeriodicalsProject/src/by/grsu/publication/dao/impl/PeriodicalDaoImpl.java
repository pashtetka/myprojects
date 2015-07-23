package by.grsu.publication.dao.impl;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import by.grsu.publication.dao.PeriodicalDao;
import by.grsu.publication.entity.PeriodicalEntity;
import by.grsu.publication.exception.LargeSizePicturesException;
import by.grsu.publication.exception.NullImageException;

public class PeriodicalDaoImpl implements PeriodicalDao {

	private static final Logger logger = Logger
			.getLogger(PeriodicalDaoImpl.class);

	private static final String GET_ALL_PERIODICALS_QUERY = "SELECT `periodicals`.`id`, `periodicals`.`periodical_Name`, `periodicals`.`cost`, `periodicals`.`outputs_In_Month`, `periodicals`.`topic` FROM `periodicals_project`.`periodicals` limit ?, ?";
	private static final String DELETE_PERIODICAL = "DELETE FROM periodicals_project.periodicals WHERE id=?";
	private static final String NEW_PERIODICALS = "INSERT INTO `periodicals_project`.`periodicals` (`periodical_Name`, `cost`, `outputs_In_Month`, `topic`, `image`) VALUES (?, ?, ?, ?, ?)";
	private static final String NEW_PERIODICALS_NULL_IMAGE = "INSERT INTO `periodicals_project`.`periodicals` (`periodical_Name`, `cost`, `outputs_In_Month`, `topic`) VALUES (?, ?, ?, ?)";
	private static final String PERIODICAL_BY_ID = "SELECT `periodicals`.`id`, `periodicals`.`periodical_Name`, `periodicals`.`cost`, `periodicals`.`outputs_In_Month`, `periodicals`.`topic` FROM `periodicals_project`.`periodicals` where `periodicals`.`id` = ? ";
	private static final String NEW_IMAGE = "UPDATE `periodicals_project`.`periodicals` SET `image`= ? WHERE `id`= ? ";
	private static final String PERIODICALS_SEARCH = "SELECT `periodicals`.`id`, `periodicals`.`periodical_Name`, `periodicals`.`cost`, `periodicals`.`outputs_In_Month`, `periodicals`.`topic` FROM `periodicals_project`.`periodicals` WHERE `periodicals`.`periodical_Name` REGEXP ? ";
	private static final String NUMBERS_PERIODICALS_SEARCH = "SELECT count(*) number FROM periodicals_project.periodicals where periodicals.periodical_Name REGEXP ? ";
	private static final String NUMBERS_PERIODICALS = "SELECT count(*) number FROM periodicals_project.periodicals ";

	private Connection connection;

	public PeriodicalDaoImpl(Connection conn) {
		this.connection = conn;
	}

	public List<PeriodicalEntity> getPeriodicals(int num) {
		List<PeriodicalEntity> periodicals = new ArrayList<PeriodicalEntity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection
					.prepareStatement(PeriodicalDaoImpl.GET_ALL_PERIODICALS_QUERY);
			stmt.setInt(1, num);
			stmt.setInt(2, 12);
			rs = stmt.executeQuery();
			while (rs.next()) {
				periodicals.add(new PeriodicalEntity(rs.getInt("id"), rs
						.getString("periodical_Name"), rs.getInt("cost"), rs
						.getInt("outputs_In_Month"), rs.getString("topic")));
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
		return periodicals;
	}

	@Override
	public void getPeriodicalDelete(int id) {
		PreparedStatement stmt = null;
		try {
			stmt = connection
					.prepareStatement(PeriodicalDaoImpl.DELETE_PERIODICAL);
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

	@Override
	public void getPeriodicalNew(String periodicalName, int cost,
			int outputsInMonth, String topic, FormFile image)
			throws LargeSizePicturesException {
		InputStream fis = null;
		PreparedStatement stmt = null;
		try {
			try {
				fis = new BufferedInputStream(image.getInputStream());
			} catch (Exception e) {
				throw new NullImageException("null image", e);
			}
			if (image.getFileSize() > 1048576) {
				throw new LargeSizePicturesException("Large Size Pictures");
			}
			stmt = connection
					.prepareStatement(PeriodicalDaoImpl.NEW_PERIODICALS);
			stmt.setString(1, periodicalName);
			stmt.setInt(2, cost);
			stmt.setInt(3, outputsInMonth);
			stmt.setString(4, topic);
			stmt.setBinaryStream(5, fis, image.getFileSize());
			stmt.executeUpdate();
		} catch (NullImageException e) {
			getPeriodicalNew(periodicalName, cost, outputsInMonth, topic);
		} catch (LargeSizePicturesException e) {
			throw new LargeSizePicturesException();
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
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					logger.error(DaoErrors.FIS_ERROR + e.getMessage());
				}
			}
		}
	}

	@Override
	public void getPeriodicalNew(String periodicalName, int cost,
			int outputsInMonth, String topic) {
		PreparedStatement stmt = null;
		try {
			stmt = connection
					.prepareStatement(PeriodicalDaoImpl.NEW_PERIODICALS_NULL_IMAGE);
			stmt.setString(1, periodicalName);
			stmt.setInt(2, cost);
			stmt.setInt(3, outputsInMonth);
			stmt.setString(4, topic);
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
	public PeriodicalEntity getPeriodicalsById(int id) {
		PeriodicalEntity periodical = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection
					.prepareStatement(PeriodicalDaoImpl.PERIODICAL_BY_ID);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				periodical = new PeriodicalEntity(rs.getInt("id"),
						rs.getString("periodical_Name"), rs.getInt("cost"),
						rs.getInt("outputs_In_Month"), rs.getString("topic"));
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
		return periodical;
	}

	@Override
	public void getNewImage(int id, FormFile image)
			throws LargeSizePicturesException, NullImageException {
		InputStream fis = null;
		PreparedStatement stmt = null;
		try {
			if (image.getFileSize() > 1048576) {
				throw new LargeSizePicturesException();
			}
			try {
				fis = new BufferedInputStream(image.getInputStream());
			} catch (Exception e) {
				throw new NullImageException("null image", e);
			}
			stmt = connection.prepareStatement(PeriodicalDaoImpl.NEW_IMAGE);
			stmt.setBinaryStream(1, fis, image.getFileSize());
			stmt.setInt(2, id);
			stmt.executeUpdate();
		} catch (NullImageException e) {
			throw new NullImageException();
		} catch (LargeSizePicturesException e) {
			throw new LargeSizePicturesException();
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
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					logger.error(DaoErrors.FIS_ERROR + e.getMessage());
				}
			}
		}
	}

	@Override
	public List<PeriodicalEntity> getPeriodicalSearch(String name) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<PeriodicalEntity> perEnt = new ArrayList<PeriodicalEntity>();
		try {
			stmt = connection
					.prepareStatement(PeriodicalDaoImpl.PERIODICALS_SEARCH);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while (rs.next()) {
				perEnt.add(new PeriodicalEntity(rs.getInt("id"), rs
						.getString("periodical_Name"), rs.getInt("cost"), rs
						.getInt("outputs_In_Month"), rs.getString("topic")));
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
		return perEnt;
	}

	@Override
	public int getNumberPeriodicals() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int number = 0;
		try {
			stmt = connection
					.prepareStatement(PeriodicalDaoImpl.NUMBERS_PERIODICALS);
			rs = stmt.executeQuery();
			if (rs.next()) {
				number = rs.getInt("number");
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
		return number;
	}

	@Override
	public int getNumberPeriodicalsSerach(String name) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int number = 0;
		try {
			stmt = connection
					.prepareStatement(PeriodicalDaoImpl.NUMBERS_PERIODICALS_SEARCH);
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			number = rs.getInt("number");
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
		return number;
	}
}
