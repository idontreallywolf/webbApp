package com.webapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.dao.AccountDao;
import com.webapp.model.Account;

@Controller
public class AccountController extends PageController implements PageControllerInterface {

	@Autowired
	AccountDao accDao;
	
	@Override
	@GetMapping("/accounts")
	public ModelAndView run() {
		ModelAndView mv = new ModelAndView("index");

		List<Account> listEmp = accDao.accList();
		mv = initDefaultAttributes("List of Accounts", null, "accounts", mv);	
		mv.addObject("listEmp", listEmp);

		return mv;
	}

}
