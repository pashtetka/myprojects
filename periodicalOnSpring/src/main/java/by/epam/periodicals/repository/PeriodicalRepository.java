package by.epam.periodicals.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import by.epam.periodicals.model.Periodical;
import by.epam.periodicals.model.enume.PeriodicalTopic;

public interface PeriodicalRepository extends CrudRepository<Periodical, Long>{
	
	List<Periodical> findByTopic(PeriodicalTopic topic);

}
