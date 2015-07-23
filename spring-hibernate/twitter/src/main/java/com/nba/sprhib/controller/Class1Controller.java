package com.nba.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nba.sprhib.dao.Class1Dao;
import com.nba.sprhib.entity.Class1;

@Controller
@RequestMapping(value = "/class1")
public class Class1Controller {
	
	@Autowired
	private Class1Dao class1Dao;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<Class1> getAllClass1() {
		return class1Dao.getAllClass1();
	}
	
}
