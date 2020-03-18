package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegisterController extends PageController implements PageControllerInterface {

	@GetMapping("/register")
    public ModelAndView run(){
        ModelAndView mv = new ModelAndView("index");
        mv = initDefaultAttributes("Register Account", "login.css", "register", mv);
        return mv;
    }
    
}
