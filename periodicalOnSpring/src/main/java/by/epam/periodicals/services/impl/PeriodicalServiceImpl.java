package by.epam.periodicals.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.epam.periodicals.model.Periodical;
import by.epam.periodicals.model.enume.PeriodicalTopic;
import by.epam.periodicals.repository.PeriodicalRepository;
import by.epam.periodicals.services.PeriodicalService;

@Service("periodicalService")
@Transactional
public class PeriodicalServiceImpl implements PeriodicalService{
	
	@Autowired
	private PeriodicalRepository periodicalRepository;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(readOnly = true)
	public List<Periodical> findAll() {
		Iterable<Periodical> pers = periodicalRepository.findAll();
		List<Periodical> list = new ArrayList<Periodical>();
		for (Periodical periodical : pers) {
			list.add(periodical);
		}
		return list;
	}

	@Override
	@Transactional(readOnly = true)
	public Periodical findById(Long id) {
		return periodicalRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Periodical> findByTopic(PeriodicalTopic topic) {
		return periodicalRepository.findByTopic(topic);
	}
	
	@Override
	@Transactional
	public void save(Periodical periodical) {
		periodicalRepository.save(periodical);	
	}
	
	@Override
	public Periodical findVersionByRevision(Long id, int version) {
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
		return auditReader.find(Periodical.class, id, version);
	}
	
	public PeriodicalRepository getPeriodicalRepository() {
		return periodicalRepository;
	}

	public void setPeriodicalRepository(PeriodicalRepository periodicalRepository) {
		this.periodicalRepository = periodicalRepository;
	}
	
	@Override
	@Scheduled(cron="0 * * * * *")
	public void methodMMM(){
		System.out.println("Method 0 ");
	}
	
	@Scheduled(cron="15,45 * * * * *")
	public void methodMT(){
		System.out.println("Method 1");
	}

}
