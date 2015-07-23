package by.epam.periodicals.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import by.epam.periodicals.model.User;
import by.epam.periodicals.services.UserService;

public class PeriodicalAudit implements AuditorAware<User> {
	
	@Autowired
	private UserService userService;

	@Override
	public User getCurrentAuditor() {
		return userService.findUserById(1L);
	}

}
