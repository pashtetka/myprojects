package by.grsu.publication.dao.base;

import java.sql.Connection;

public interface ConnectionProvider {
	public Connection getConnection();
}
