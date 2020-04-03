package com.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.webapp.Config;
import com.webapp.dao.AccountDao;
import com.webapp.model.RegisterForm;

@Controller
public class RegisterController extends PageController implements PageControllerInterface {

    @Autowired
	AccountDao accDao;
	@Autowired
	PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public ModelAndView run(HttpServletRequest req, HttpSession session, HttpServletResponse hsRes){
    	
    	// Redirect user if a session is already set.
    	if(session.getAttribute("sessID") != null) {
    		redirect("/", hsRes);
    	}
    	
        ModelAndView mv = new ModelAndView("index");
        mv = initDefaultAttributes("Register Account", "login.css", "register", mv);
        return mv;
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, String> registerAccount(@RequestBody RegisterForm regForm, HttpSession session){
    	Map<String, String> errors = new HashMap<String, String>();
    	
    	if(session.getAttribute("sessID") != null) {
    		errors.put("error", "err#sess");
    		return errors;
    	}
    	
    	// keeps count of errors
    	int errCounter = 0;
    	
    	// Validate fields and append results to errors map
    	String fname = validateName(regForm.getFirstname());
    	String lname = validateName(regForm.getLastname());
    	String uname = validateUsername(regForm.getUsername());
    	String pass  = validatePassword(regForm.getPassword(), regForm.getPasswordConfirm());
    	String email = validateEmail(regForm.getEmail());
    	
        errors.put("firstname", fname);
        errors.put("lastname",  lname);
        errors.put("username",  uname);
        errors.put("password",  pass);
        errors.put("email",     email);
        
        // count errors
        errCounter += fname.replaceAll("\\[\\]", "").length() > 0 ? 1:0;
        errCounter += lname.replaceAll("\\[\\]", "").length() > 0 ? 1:0;
        errCounter += uname.replaceAll("\\[\\]", "").length() > 0 ? 1:0;
        errCounter += pass .replaceAll("\\[\\]", "").length() > 0 ? 1:0;
        errCounter += email.replaceAll("\\[\\]", "").length() > 0 ? 1:0;
        
        // Attempt to create account if no errors occured.
        if(errCounter == 0) {
        	if(accDao.registerAccount(
        			regForm.getFirstname(), 
        			regForm.getLastname(), 
        			regForm.getUsername(), 
        			passwordEncoder.encode(regForm.getPassword()), 
        			regForm.getEmail()) == false)
        		errors.put("error", "err#reg");
        }
        
        errors.put("counter", Integer.toString(errCounter));
        
        return errors;
    }


    /**
    *	Validates names
    * 	@param name
    *
    *	@return a String representation of a List<String> containing errors ( if any. Otherwise an empty [string] )
    * */
    public String validateName(String name){
        List<String> errors = new ArrayList<String>();

        if(name.length() < Config.Account.minNameLength)
            errors.add("err#minLen");
        else if(name.length() > Config.Account.maxNameLength)
            errors.add("err#maxLen");

        // Check for non-alpha characters; symbols & numbers
        Pattern p = Pattern.compile("[^a-zA-Z]+?");
        Matcher m = p.matcher(name);

        if (m.find())
            errors.add("err#sym");

        return errors.toString();
    }


    /**
    *   Validates the username
    *
    *   @param uname - username to be validated
    *   @return a String representation of a List<String> containing errors ( if any. Otherwise an empty [string] )
    */
    public String validateUsername(String uname){
        List<String> errors = new ArrayList<String>();

        if(uname.length() < Config.Account.minUnameLength)
            errors.add("err#minLen");
        else if(uname.length() > Config.Account.maxUnameLength)
            errors.add("err#maxLen");

        // Validate against non-alpha(&-numeric)
        Pattern p = Pattern.compile("[^a-zA-Z0-9]");
        Matcher m = p.matcher(uname);

        if (m.find())
            errors.add("err#sym");

        if(accDao.getAccountByUsername(uname) != null)
            errors.add("err#taken");

        return errors.toString();
    }

    /**
    *   Validates password
    *
    *   @param password
    *   @return a String representation of a List<String> containing errors ( if any. Otherwise an empty [string] )
    */
    public String validatePassword(String password, String passwordConfirm) {
        List<String> errors = new ArrayList<String>();

        if(!password.equals(passwordConfirm))
            errors.add("err#cnfrm");

        if(password.length() < Config.Account.minPassLength)
            errors.add("err#minLen");
        else if(password.length() > Config.Account.maxPassLength)
            errors.add("err#maxLen");

        // Check if password contains atleast 1 ..
        // .. of each ( upperCase, lowerCase, number )
        boolean lowerCase = false;
        boolean upperCase = false;
        boolean isDigit   = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if(Character.isDigit(ch))
                isDigit = true;
            else if(Character.isUpperCase(ch))
                upperCase = true;
            else if(Character.isLowerCase(ch))
                lowerCase = true;

            if(isDigit && upperCase && lowerCase)
                break;
        }

        if(!lowerCase || !upperCase || !isDigit)
            errors.add("err#badSec");

        return errors.toString();
    }

    /**
    *   Validates email
    *
    *   @param email
    *   @return a String representation of a List<String> containing errors ( if any. Otherwise an empty [string] )
    */
    public String validateEmail(String email) {
        List<String> errors = new ArrayList<String>();

        if(email.length() < Config.Account.minEmailLength)
        	errors.add("err#len");

        String regexPattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
                            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

        Pattern p = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);

        if (!m.find())
            errors.add("err#inv");

        if(accDao.getAccountByEmail(email) != null)
            errors.add("err#taken");

        return errors.toString();
    }
}
