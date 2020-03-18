package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController extends PageController implements PageControllerInterface {

	@Override
	@GetMapping("/")
	public ModelAndView run() {
		ModelAndView mv = new ModelAndView("index");
		mv = initDefaultAttributes("Welcome | Pepe", null, "main", mv);
		return mv;
	}

}
