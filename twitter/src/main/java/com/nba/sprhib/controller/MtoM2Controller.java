package com.nba.sprhib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nba.sprhib.dao.MtoM2Dao;
import com.nba.sprhib.entity.MtoM2;

@Controller
@RequestMapping(value = "/mtom2")
public class MtoM2Controller {
	
	@Autowired
	private MtoM2Dao mtoM2Dao;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@ResponseBody
	public List<MtoM2> getAllMtoM2() {
		return mtoM2Dao.getAllMtoM2();
	}
	
}
