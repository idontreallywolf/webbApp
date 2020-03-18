package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController extends PageController implements PageControllerInterface {

	@GetMapping("/login")
    public ModelAndView run(){
        ModelAndView mv = new ModelAndView("index");
        mv = initDefaultAttributes("Login", "login.css", "login", mv);
        return mv;
    }

}
