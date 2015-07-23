package by.epam.periodicals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.epam.periodicals.model.User;
import by.epam.periodicals.services.UserService;

@Component("userController")
public class UserController {
	
	@Autowired
	private UserService userService;

	public List<User> getUserList(){
		return userService.findAll();
	}
	
}
