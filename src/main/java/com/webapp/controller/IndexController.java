package com.webapp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController extends PageController implements PageControllerInterface {

	@Override
	@GetMapping("/")
	public ModelAndView run(HttpSession session, HttpServletResponse hsRes) {
		ModelAndView mv = new ModelAndView("index");
		mv = initDefaultAttributes("Welcome | Pepe", null, "main", mv);
		mv.addObject("sessID", session.getAttribute("sessID"));
		mv.addObject("username", session.getAttribute("username"));
		if(session.getAttribute("sessID") != null) {
			mv.addObject("username", session.getAttribute("username"));
		}
		return mv;
	}

}
