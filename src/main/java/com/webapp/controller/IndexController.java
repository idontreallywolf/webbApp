package com.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.dao.PostDao;

@Controller
public class IndexController extends PageController implements PageControllerInterface {

    @Autowired
    PostDao postDao;

	@Override
	@GetMapping("/")
	public ModelAndView run(HttpServletRequest req, HttpSession session, HttpServletResponse hsRes) {
		ModelAndView mv = new ModelAndView("index");
		mv = initDefaultAttributes("Welcome | Pepe", null, "main", mv);

		if(session.getAttribute("sessID") != null) {
            mv.addObject("sessID", session.getAttribute("sessID"));
			mv.addObject("username", session.getAttribute("username"));
		}

        mv.addObject("posts", postDao.getPosts());

		return mv;
	}

}
