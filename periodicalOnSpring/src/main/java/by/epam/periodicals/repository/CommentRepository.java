package by.epam.periodicals.repository;

import org.springframework.data.repository.CrudRepository;

import by.epam.periodicals.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long>{
		

}
