package by.grsu.publication.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.grsu.publication.dao.DaoManager;
import by.grsu.publication.dao.SubscribeDao;
import by.grsu.publication.dao.base.ConnectionBehavior;
import by.grsu.publication.dao.base.DaoCommand;
import by.grsu.publication.entity.SubscribeEntity;
import by.grsu.publication.model.Subscribe;
import by.grsu.publication.service.SubscribeService;

public class SubscribeServiceImpl implements SubscribeService {

	private ServiceConvert conv = new ServiceConvert();

	private DaoManager daoManager;

	public SubscribeServiceImpl(DaoManager daoManager) {
		this.daoManager = daoManager;
	}

	public DaoManager getDaoManager() {
		return daoManager;
	}

	@Override
	public void getCartNew(final int periodicalId, final int userId) {
		getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				final SubscribeDao subscribeDao = daoManager.getDao(SubscribeDao.class);
				subscribeDao.getCartNew(periodicalId, userId);
				return null;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Subscribe> getAllSubscribe() {
		return (List<Subscribe>) getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				List<SubscribeEntity> subscribesEnt = null;
				List<Subscribe> subscribes = new ArrayList<Subscribe>();
				final SubscribeDao subscribeDao = daoManager.getDao(SubscribeDao.class);
				subscribesEnt = subscribeDao.getAllSubscribe();
				if (!subscribesEnt.isEmpty()) {
					for (SubscribeEntity subEnt : subscribesEnt) {
						subscribes.add(conv.convertSub(subEnt));
					}
				}
				return subscribes;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@Override
	public void getDeleteBasketById(final int userId, final int periodicalId) {
		getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				final SubscribeDao subscribeDao = daoManager.getDao(SubscribeDao.class);
				subscribeDao.getDeleteBasketById(userId, periodicalId);
				return null;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@Override
	public void getNewSubscribe(final int periodicalId, final int userId) {
		getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				final SubscribeDao subscribeDao = daoManager.getDao(SubscribeDao.class);
				subscribeDao.getNewSubscribe(periodicalId, userId);
				return null;
			}
		}, ConnectionBehavior.CLOSE);

	}
}
