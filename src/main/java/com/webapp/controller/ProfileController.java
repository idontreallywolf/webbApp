package com.webapp.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.dao.AccountDao;
import com.webapp.dao.PostDao;
import com.webapp.model.Account;

@Controller
public class ProfileController extends PageController implements PageControllerInterface {

	@Autowired
	AccountDao accDao;
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
        
        mv.addObject("uid", session.getAttribute("userId"));
        mv.addObject("firstname", session.getAttribute("firstname"));
        mv.addObject("lastname", session.getAttribute("lastname"));

        // fetch user posts
        mv.addObject("posts", postDao.getPostsByAuthorId(
            (int)session.getAttribute("sessID"))
        );

        return mv;
    }
    
    
    @GetMapping("/profile/{userID}")
    public ModelAndView profile(@PathVariable(value="userID") int userID, HttpSession session, HttpServletResponse hsRes) {
    	ModelAndView mv = new ModelAndView("index");
    	mv = initDefaultAttributes("Profile", "profile.css", "profile", mv);
        
    	Account acc = accDao.getAccountById(userID);
    	
    	// fetch user posts
        mv.addObject("posts", postDao.getPostsByAuthorId(userID));
        
        mv.addObject("uid", acc.getId());
        mv.addObject("firstname", acc.getFirstname());
        mv.addObject("lastname", acc.getLastname());
        
    	return mv;
    }

}
