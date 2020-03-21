package com.webapp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.dao.AccountDao;
import com.webapp.model.Account;
import com.webapp.model.LoginForm;

@Controller
public class LoginController extends PageController implements PageControllerInterface {
	
	@Autowired
	AccountDao accDao;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/login")
    public ModelAndView run(HttpSession session, HttpServletResponse hsRes){
        ModelAndView mv = new ModelAndView("index");
        mv = initDefaultAttributes("Login", "login.css", "login", mv);
        return mv;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseObject login(@RequestBody LoginForm loginFormInfo, HttpSession session){

    	// Retrieve account
    	Account acc = accDao.getAccountByUsername(loginFormInfo.getUsername());
    	if(acc == null)
    		return new ResponseObject("Account Not found");
    	
    	// compare passwords
    	if(passwordEncoder.matches(loginFormInfo.getPassword(), acc.getPassword())) {
    		session.setAttribute("sessID", acc.getId());
    		session.setAttribute("username", acc.getUsername());
    	}
    	
    	System.out.println("SESSION_ID: "+session.getAttribute("sessID"));
    	System.out.println("USERNAME: "+session.getAttribute("username"));

        return new ResponseObject("Uknown username or password");
    }

}
