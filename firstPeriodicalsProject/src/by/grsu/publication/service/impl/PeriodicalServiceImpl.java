package by.grsu.publication.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;

import java.lang.Math;

import by.grsu.publication.dao.CommentDao;
import by.grsu.publication.dao.DaoManager;
import by.grsu.publication.dao.PeriodicalDao;
import by.grsu.publication.dao.SubscribeDao;
import by.grsu.publication.dao.base.ConnectionBehavior;
import by.grsu.publication.dao.base.DaoCommand;
import by.grsu.publication.entity.PeriodicalEntity;
import by.grsu.publication.entity.SubscribeEntity;
import by.grsu.publication.exception.LargeSizePicturesException;
import by.grsu.publication.exception.NullImageException;
import by.grsu.publication.model.IPeriodical;
import by.grsu.publication.service.PeriodicalService;

public class PeriodicalServiceImpl implements PeriodicalService {

	private ServiceConvert conv = new ServiceConvert();
	private DaoManager daoManager;

	public PeriodicalServiceImpl(DaoManager daoManager) {
		this.daoManager = daoManager;
	}

	public DaoManager getDaoManager() {
		return daoManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IPeriodical> getPeriodicals(final int page) {
		return (List<IPeriodical>) getDaoManager().transaction(
				new DaoCommand() {
					public Object execute(final DaoManager manager) {
						final PeriodicalDao periodicalDao = manager.getDao(PeriodicalDao.class);
						List<PeriodicalEntity> periodicalsEnt = new ArrayList<PeriodicalEntity>();
						int num = (page - 1) * 12;
						periodicalsEnt = periodicalDao.getPeriodicals(num);
						final List<IPeriodical> periodicals = new ArrayList<IPeriodical>();
						for (final PeriodicalEntity perEnt : periodicalsEnt) {
							periodicals.add(conv.convertPer(perEnt));
						}
						return periodicals;
					}
				}, ConnectionBehavior.CLOSE);
	}

	@Override
	public void getPeriodicalDelete(final int id) {
		getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				final CommentDao commentDao = daoManager.getDao(CommentDao.class);
				final PeriodicalDao periodicalDao = daoManager
						.getDao(PeriodicalDao.class);
				final SubscribeDao subscribeDao = daoManager
						.getDao(SubscribeDao.class);
				commentDao.getDeleteComment(id);
				subscribeDao.getDeleteSubscribeById(id);
				periodicalDao.getPeriodicalDelete(id);
				return null;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@Override
	public void getPeriodicalNew(final String periodicalName, final int cost,
			final int outputsInMonth, final String topic, final FormFile image)
			throws LargeSizePicturesException {
		try {
			getDaoManager().transaction(new DaoCommand() {
				@Override
				public Object execute(DaoManager daoManager) {
					final PeriodicalDao periodicalDao = daoManager.getDao(PeriodicalDao.class);
					try {
						periodicalDao.getPeriodicalNew(periodicalName, cost,
								outputsInMonth, topic, image);
					} catch (LargeSizePicturesException e) {
						throw new RuntimeException(e);
					}
					return null;
				}
			}, ConnectionBehavior.CLOSE);
		} catch (Exception e) {
			throw new LargeSizePicturesException();
		}
	}

	@Override
	public IPeriodical getPeriodicalsById(final int id) {
		return (IPeriodical) getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				final PeriodicalDao periodicalDao = daoManager.getDao(PeriodicalDao.class);
				PeriodicalEntity periodicalEnt = null;
				IPeriodical periodical = null;
				periodicalEnt = periodicalDao.getPeriodicalsById(id);
				if (periodicalEnt != null) {
					periodical = conv.convertPer(periodicalEnt);
				}
				return periodical;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IPeriodical> getUserBasket(final int id, final String status) {
		return (List<IPeriodical>) getDaoManager().transaction(
				new DaoCommand() {
					@Override
					public Object execute(final DaoManager daoManager) {
						List<SubscribeEntity> subscribeEnt = null;
						PeriodicalEntity periodical = null;
						List<IPeriodical> periodicalsBasket = new ArrayList<IPeriodical>();
						SubscribeDao subscribeDao = daoManager
								.getDao(SubscribeDao.class);
						PeriodicalDao periodicalDao = daoManager
								.getDao(PeriodicalDao.class);
						subscribeEnt = subscribeDao.getUserBasket(id, status);
						if (!subscribeEnt.isEmpty()) {
							for (SubscribeEntity subEnt : subscribeEnt) {
								periodical = periodicalDao
										.getPeriodicalsById(subEnt
												.getPeriodicalId());
								if (periodical != null) {
									periodicalsBasket.add(conv
											.convertPer(periodical));
								}
							}
						}
						return periodicalsBasket;
					}
				}, ConnectionBehavior.CLOSE);
	}

	@Override
	public String getNewImage(final int id, final FormFile image) {
		return (String) getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				String message = null;
				final PeriodicalDao periodicalDao = daoManager.getDao(PeriodicalDao.class);
				try {
					periodicalDao.getNewImage(id, image);
				} catch (NullImageException e) {
					message = "nellImage";
				} catch (LargeSizePicturesException e) {
					message = "largeSizePictures";
				}
				return message;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IPeriodical> getPeriodicalSearch(final String name) {
		return (List<IPeriodical>) getDaoManager().transaction(
				new DaoCommand() {
					@Override
					public Object execute(DaoManager daoManager) {
						List<PeriodicalEntity> perEnt = null;
						List<IPeriodical> periodicals = new ArrayList<IPeriodical>();
						final PeriodicalDao periodicalDao = daoManager
								.getDao(PeriodicalDao.class);
						perEnt = periodicalDao.getPeriodicalSearch(name);
						if (!perEnt.isEmpty()) {
							for (PeriodicalEntity persEnt : perEnt) {
								periodicals.add(conv.convertPer(persEnt));
							}
						}
						return periodicals;
					}
				}, ConnectionBehavior.CLOSE);
	}

	@Override
	public int getNumberPeriodicals() {
		return (int) getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				PeriodicalDao periodicalDao = daoManager.getDao(PeriodicalDao.class);
				int number = periodicalDao.getNumberPeriodicals();
				int num = (int) Math.ceil((float) number / 10);
				if (num == 0) {
					num = 1;
				}
				return num;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@Override
	public int getNumberPeriodicalsSerach(final String name) {
		return (int) getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				PeriodicalDao periodicalDao = daoManager.getDao(PeriodicalDao.class);
				int number = periodicalDao.getNumberPeriodicalsSerach(name);
				int num = (int) Math.ceil((float) number / 10);
				if (num == 0) {
					num = 1;
				}
				return num;
			}
		}, ConnectionBehavior.CLOSE);
	}

}
