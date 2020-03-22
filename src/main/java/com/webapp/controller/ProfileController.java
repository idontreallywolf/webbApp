package com.webapp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProfileController extends PageController implements PageControllerInterface {
    
    @GetMapping("/profile")
	public ModelAndView run(HttpSession session, HttpServletResponse hsRes) {
		ModelAndView mv = new ModelAndView("index");
        mv = initDefaultAttributes("Profile", null, "profile", mv);
		return mv;
	}

}
