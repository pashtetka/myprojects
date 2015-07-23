package com.nba.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nba.sprhib.dao.Class2Dao;
import com.nba.sprhib.entity.Class2;

@Controller
@RequestMapping(value = "/class2")
public class Class2Controller {
	
	@Autowired
	private Class2Dao class2Dao;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Class2> getAllClass2() {
		return class2Dao.getAllClass2();
	}

}
