package by.epam.periodicals.repository;

import org.springframework.data.repository.CrudRepository;

import by.epam.periodicals.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}
