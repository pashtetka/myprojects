package by.epam.periodicals.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.periodicals.model.Comment;
import by.epam.periodicals.repository.CommentRepository;
import by.epam.periodicals.services.CommentService;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepository commentRepository; 

	@Override
	@Transactional(readOnly = true)
	public List<Comment> findAll() {
		Iterable<Comment> it = commentRepository.findAll();
		List<Comment> list = new ArrayList<Comment>();
		for (Comment comment : it) {
			list.add(comment);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Comment findBuId(Long id) {
		return commentRepository.findOne(id);
	}

	@Override
	public void save(Comment comment) {
		commentRepository.save(comment);
	}

}
