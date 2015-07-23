package by.grsu.publication.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import by.grsu.publication.dao.base.ConnectionProvider;
import by.grsu.publication.dao.impl.CommentDaoImpl;
import by.grsu.publication.dao.impl.PeriodicalDaoImpl;
import by.grsu.publication.dao.impl.SubscribeDaoImpl;
import by.grsu.publication.dao.impl.UserDaoImpl;
import by.grsu.publication.dao.base.ConnectionBehavior;
import by.grsu.publication.dao.base.DaoCommand;

public class DaoManager {
	
	private static final Logger logger = Logger
			.getLogger(DaoManager.class);

	private static final String JNDI_DB_REF = "java:/comp/env/jdbc/appDb";

	@SuppressWarnings("rawtypes")
	private Map<Class, Object> daoObjects;
	private Connection connection;
	private ConnectionProvider provider;

	@SuppressWarnings("rawtypes")
	private DaoManager(ConnectionProvider provider) {
		super();
		this.provider = provider;
		this.daoObjects = new HashMap<Class, Object>();
	}

	private void refreshState() {
		try {
			if (getConnection() == null || getConnection().isClosed()) {

				this.connection = this.provider.getConnection();
				daoObjects.put(PeriodicalDao.class, new PeriodicalDaoImpl(
						getConnection()));
				daoObjects.put(SubscribeDao.class, new SubscribeDaoImpl(
						getConnection()));
				daoObjects.put(UserDao.class, new UserDaoImpl(getConnection()));
				daoObjects.put(CommentDao.class, new CommentDaoImpl(
						getConnection()));
			}
		} catch (SQLException e) {
			this.connection = provider.getConnection();
		}
	}

	private Connection getConnection() {
		return this.connection;
	}

	public static DaoManager getDefault() {
		return new DaoManager(new ConnectionProvider() {
			@Override
			public Connection getConnection() {
				try {
					DataSource dataSource = (DataSource) new InitialContext()
							.lookup(DaoManager.JNDI_DB_REF);
					return dataSource.getConnection();
				} catch (Exception e) {
					logger.error(e.getMessage());
					throw new RuntimeException(e);
				}

			}
		});
	}

	public Object transaction(DaoCommand command, ConnectionBehavior behavior) {
		try {
			this.refreshState();
			getConnection().setAutoCommit(false);
			Object returnValue = command.execute(this);
			getConnection().commit();

			return returnValue;
		} catch (Exception e) {

			try {
				getConnection().rollback();
			} catch (SQLException e1) {
				logger.error(e1.getMessage());
				throw new RuntimeException(e1);
			}
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		} finally {

			try {
				getConnection().setAutoCommit(true);
				if (ConnectionBehavior.CLOSE.equals(behavior)) {
					getConnection().close();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new RuntimeException(e);
			}
		}
	}

	public Object command(DaoCommand command, ConnectionBehavior behavior) {

		this.refreshState();
		Object result = command.execute(this);

		if (ConnectionBehavior.CLOSE.equals(behavior)) {
			try {
				getConnection().close();
			} catch (SQLException e) {
				logger.error(e.getMessage());
				throw new RuntimeException(e);
			}
		}
		return result;
	}

	public <T> T getDao(Class<?> daoInterface) {
		@SuppressWarnings("unchecked")
		final T dao = (T) daoObjects.get(daoInterface);

		if (dao == null) {
			throw new IllegalArgumentException("Unsupported DAO type:"
					+ daoInterface.getClass().getName());
		}

		return dao;
	}

}
