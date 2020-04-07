package com.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

public interface PageControllerInterface {

	/**
	* Run method acts like main method for each pageController
    */
	public abstract ModelAndView run(HttpServletRequest req, HttpSession session, HttpServletResponse hsRes);
}
