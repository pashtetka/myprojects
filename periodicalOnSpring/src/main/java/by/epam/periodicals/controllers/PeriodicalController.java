package by.epam.periodicals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.epam.periodicals.model.Periodical;
import by.epam.periodicals.services.PeriodicalService;

@Controller
@RequestMapping(value = "/p")
public class PeriodicalController {
	
	@Autowired
	private PeriodicalService periodicalService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<Periodical> getPeriodicalList(){
		return periodicalService.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String getPeriodicalById(@PathVariable Long id, ModelMap model) {
		Periodical periodical = periodicalService.findById(id);
		model.addAttribute("periodical", periodical);
		return "index";
	}

}
