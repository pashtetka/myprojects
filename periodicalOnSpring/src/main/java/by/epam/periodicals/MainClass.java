package by.epam.periodicals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.GenericXmlApplicationContext;

import by.epam.periodicals.model.Proba;

public class MainClass {
	
	private static final Logger LOG = LogManager.getLogger(MainClass.class);

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:root-context.xml");
		ctx.refresh();
		
		Proba proba = ctx.getBean("proba", Proba.class);
		System.out.println(proba.getProba());
		
		while (true) {
			
		}

//		UserService userService = ctx.getBean("userService", UserService.class);
//		CommentService commentService = ctx.getBean("commentService", CommentService.class);
//		PeriodicalService periodicalService = ctx.getBean("periodicalService", PeriodicalService.class);
//		
//		User user = userService.findUserById(1L);
//		user.setUserName("asdfassdfd");
//		userService.save(user);
//		
//		Periodical periodical = new Periodical();
//		periodical.setCost(341);
//		periodical.setOutputsInMonth(2);
//		periodical.setPeriodicalName("afsgadf");
//		periodical.setTopic(PeriodicalTopic.FASHION);
//		periodicalService.save(periodical);
//		
//		Periodical periodical2 = new Periodical();
//		periodical2.setCost(345);
//		periodical2.setOutputsInMonth(3);
//		periodical2.setPeriodicalName("ergtwerg");
//		periodical2.setTopic(PeriodicalTopic.CHILD);
//		periodicalService.save(periodical2);
//		
//		Periodical newPeriodical = periodicalService.findById(1L);
//		System.out.println("-------- BY NAME -------------------------------------------");
//		System.out.println(newPeriodical.getPeriodicalName());
//		System.out.println(newPeriodical);
//		System.out.println("------------------------------------------------------------");
//		
//		List<Periodical> newPeriodical2 = periodicalService.findByTopic(PeriodicalTopic.CHILD);
//		System.out.println("-------- BY TOPIC ------------------------------------------");
//		for (Periodical periodical3 : newPeriodical2) {
//			System.out.println(periodical3.getTopic() + "   " + periodical3.getPeriodicalName());
//			System.out.println(periodical3);
//		}
//		System.out.println("------------------------------------------------------------");
//		
//		List<Periodical> perList = periodicalService.findAll();
//		System.out.println("------ FIND ALL --------------------------------------------");
//		for (Periodical periodical3 : perList) {
//			System.out.println(periodical3.getId() + "   " + periodical3.getPeriodicalName());
//			System.out.println(periodical3);
//
//		}
//		System.out.println("------------------------------------------------------------");
////		
//		System.out.println("------ MODIFIED --------------------------------------------");
//		newPeriodical.setTopic(PeriodicalTopic.LIVE);
//		periodicalService.save(newPeriodical);
//		System.out.println("------------------------------------------------------------");
//		
//		System.out.println("------ OLD VERSION -----------------------------------------");
//		Periodical oldVersion = periodicalService.findVersionByRevision(1L, 2);
//		System.out.println(oldVersion);
//		System.out.println("------------------------------------------------------------");
		
//		ctx.close();
		
	}

}
