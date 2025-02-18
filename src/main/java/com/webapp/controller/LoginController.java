package com.webapp.controller;

import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView run(HttpServletRequest req, HttpSession session, HttpServletResponse hsRes){

        // Redirect user if a session is already set.
        if(session.getAttribute("sessID") != null){
            redirect("/", hsRes);
            return null;
        }

        ModelAndView mv = new ModelAndView("index");
        mv = initDefaultAttributes("Login", "login.css", "login", mv);
        return mv;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseObject login(@RequestBody LoginForm loginFormInfo, HttpSession session){

        if(session.getAttribute("sessID") != null) {
            // session already active
            return new ResponseObject("err#active");
        }

        // Retrieve account
        Account acc = accDao.getAccountByUsername(loginFormInfo.getUsername());
        if(acc == null) {
            // not found
            return new ResponseObject("err#noacc");
        }

        // compare passwords
        if(passwordEncoder.matches(loginFormInfo.getPassword(), acc.getPassword())) {
            setSession(acc, session);
            return new ResponseObject("success");
        }

        // wrong username or password
        return new ResponseObject("err#wuop");
    }

    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse hsRes){
        if(session.getAttribute("sessID") != null)
            session.invalidate();

        redirect("/", hsRes);
    }

    private void setSession(Account acc, HttpSession session) {
        session.setAttribute("sessID", acc.getId());
        session.setAttribute("firstname", acc.getFirstname());
        session.setAttribute("lastname", acc.getLastname());
        session.setAttribute("username", acc.getUsername());
        session.setAttribute("email", acc.getEmail());
        // TODO: user Roles
    }

}
