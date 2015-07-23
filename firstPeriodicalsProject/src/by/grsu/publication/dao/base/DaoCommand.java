package by.grsu.publication.dao.base;

import by.grsu.publication.dao.DaoManager;

public interface DaoCommand {	
	public Object execute(DaoManager daoManager);
}
