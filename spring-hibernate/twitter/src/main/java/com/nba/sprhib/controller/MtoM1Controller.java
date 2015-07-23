package com.nba.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nba.sprhib.dao.MtoM1Dao;
import com.nba.sprhib.entity.MtoM1;

@Controller
@RequestMapping(value = "/mtom1")
public class MtoM1Controller {
	
	@Autowired
	private MtoM1Dao mtoM1Dao;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<MtoM1> getAllMtoM1() {
		return mtoM1Dao.getAllMtoM1();
	}
	
}
