package by.grsu.publication.service;

import java.util.List;

import by.grsu.publication.model.Comments;

public interface CommentService {

	public void getNewComment(final int periodicalId, final int userId,
			final String comm);
	
	public List<Comments> getPeriodicalComment(final int periodicalId);
	
	public void getDeleteCommentBuId(final int id);

}
