package by.epam.periodicals.services;

import java.util.List;

import by.epam.periodicals.model.Periodical;
import by.epam.periodicals.model.enume.PeriodicalTopic;

public interface PeriodicalService {
	
	List<Periodical> findAll();

	Periodical findById(Long id);
	
	List<Periodical> findByTopic(PeriodicalTopic topic);
	
	void save(Periodical periodical);
	
	Periodical findVersionByRevision(Long id, int version);
	
	void methodMMM();
	
	void methodMT();
}
