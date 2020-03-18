package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController extends PageController implements PageControllerInterface {
	
	@GetMapping("/about")
	public ModelAndView run() {
		ModelAndView mv = new ModelAndView("index");
		mv = initDefaultAttributes("About", null, "about", mv);		
		return mv;
	}
	
}
