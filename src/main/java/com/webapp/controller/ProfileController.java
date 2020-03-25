package com.webapp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.dao.PostDao;

@Controller
public class ProfileController extends PageController implements PageControllerInterface {

	@Autowired
	PostDao postDao;

    @GetMapping("/profile")
    public ModelAndView run(HttpSession session, HttpServletResponse hsRes) {

        // Redirect user if there's no active session
        if(session.getAttribute("sessID") == null) {
            hsRes.setHeader("location", "/");
            hsRes.setStatus(302);
            return null;
        }

        ModelAndView mv = new ModelAndView("index");
        mv = initDefaultAttributes("Profile", "profile.css", "profile", mv);

        // fetch user posts
        mv.addObject("posts", postDao.getPostsByAuthorId(
            (int)session.getAttribute("sessID"))
        );

        return mv;
    }

}
