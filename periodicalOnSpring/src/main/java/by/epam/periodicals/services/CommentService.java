package by.epam.periodicals.services;

import java.util.List;

import by.epam.periodicals.model.Comment;

public interface CommentService {

	List<Comment> findAll();
	
	Comment findBuId(Long id);
	
	void save(Comment comment);
	
}
