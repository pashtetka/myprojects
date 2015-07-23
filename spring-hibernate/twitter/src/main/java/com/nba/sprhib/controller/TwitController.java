package com.nba.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nba.sprhib.dao.TwitDao;
import com.nba.sprhib.entity.TwitEntity;
import com.nba.sprhib.exceptions.NullResultException;

@Controller
@RequestMapping(value = "/twits")
public class TwitController {
	
	@Autowired
	private TwitDao twitDao;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<TwitEntity> getAllTwits() {
		return twitDao.getAllTwits();
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public List<TwitEntity> getByUserId(@PathVariable Long userId) throws NullResultException {
		return twitDao.getByUserId(userId);
	}

}
