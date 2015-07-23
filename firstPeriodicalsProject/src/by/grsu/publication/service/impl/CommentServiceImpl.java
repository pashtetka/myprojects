package by.grsu.publication.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.grsu.publication.dao.CommentDao;
import by.grsu.publication.dao.DaoManager;
import by.grsu.publication.model.Comments;
import by.grsu.publication.service.CommentService;
import by.grsu.publication.dao.base.ConnectionBehavior;
import by.grsu.publication.dao.base.DaoCommand;
import by.grsu.publication.entity.CommentsEntity;

public class CommentServiceImpl implements CommentService {

	private ServiceConvert conv = new ServiceConvert();
	private DaoManager daoManager;

	public CommentServiceImpl(DaoManager daoManager) {
		this.daoManager = daoManager;
	}

	public DaoManager getDaoManager() {
		return daoManager;
	}

	@Override
	public void getNewComment(final int periodicalId, final int userId,
			final String comm) {
		getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				final CommentDao commentDao = daoManager.getDao(CommentDao.class);
				commentDao.getNewComment(periodicalId, userId, comm);
				return null;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comments> getPeriodicalComment(final int periodicalId) {
		return (List<Comments>) getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				List<CommentsEntity> commentsEnt = null;
				List<Comments> comm = new ArrayList<Comments>();
				final CommentDao commentDao = daoManager.getDao(CommentDao.class);
				commentsEnt = commentDao.getPeriodicalComments(periodicalId);
				for (CommentsEntity commEnt : commentsEnt) {
					comm.add(conv.convertCom(commEnt));
				}
				return comm;
			}
		}, ConnectionBehavior.CLOSE);
	}

	@Override
	public void getDeleteCommentBuId(final int id) {
		getDaoManager().transaction(new DaoCommand() {
			@Override
			public Object execute(DaoManager daoManager) {
				final CommentDao commentDao = daoManager.getDao(CommentDao.class);
				commentDao.getDeleteCommentById(id);
				return null;
			}
		}, ConnectionBehavior.CLOSE);

	}

}
