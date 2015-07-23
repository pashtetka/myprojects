package com.nba.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nba.sprhib.dao.UserDao;
import com.nba.sprhib.entity.UserEntity;

@Controller
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<UserEntity> getAllUsers() {
		return userDao.getAllUsers();
	}

}
