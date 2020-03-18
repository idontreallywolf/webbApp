package com.webapp.controller;

import org.springframework.web.servlet.ModelAndView;

public interface PageControllerInterface {
	
	/**
	 * Run method acts like main method for each page request
	 * */
	public abstract ModelAndView run();
}
