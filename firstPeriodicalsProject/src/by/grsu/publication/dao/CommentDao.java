package by.grsu.publication.dao;

import java.util.List;

import by.grsu.publication.entity.CommentsEntity;

public interface CommentDao {
	
	public void getNewComment(int periodicalId, int userId, String comm);
	
	public List<CommentsEntity> getPeriodicalComments(int periodicalId);
	
	public void getDeleteComment(int periodicalId);
	
	public void getDeleteCommentById(int id);

}
