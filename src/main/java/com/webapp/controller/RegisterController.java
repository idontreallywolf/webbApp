package com.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.webapp.Config;
import com.webapp.model.Account;
import com.webapp.model.RegisterForm;

@Controller
public class RegisterController extends PageController implements PageControllerInterface {

    @GetMapping("/register")
    public ModelAndView run(){
        ModelAndView mv = new ModelAndView("index");
        mv = initDefaultAttributes("Register Account", "login.css", "register", mv);
        return mv;
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, List<String>> registerAccount(@RequestBody RegisterForm regForm){
        Map<String, List<String>> errors = new HashMap<String, List<String>>();

        errors.put("firstname", validateName(regForm.getFirstname()));
        errors.put("lastname",  validateName(regForm.getLastname()));
        errors.put("username",  validateUsername(regForm.getUsername()));
        errors.put("password",  validatePassword(regForm.getPassword(), regForm.getPasswordConfirm()));
        errors.put("email",     validateEmail(regForm.getEmail()));

        return errors;
    }


    /**
    *	Validates names
    * 	@param name
    *
    *	@return a List<String> of errors ( if any. Otherwise an empty list )
    * */
    public List<String> validateName(String name){
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

        return errors;
    }


    /**
    *   Validates the username
    *
    *   @param uname - username to be validated
    *   @return a List<String> of errors ( if any. Otherwise an empty list )
    */
    public List<String> validateUsername(String uname){
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

        // TODO: check if username is already in use

        return errors;
    }

    /**
    *   Validates password
    *
    *   @param password
    *   @return  a List<String> of errors ( if any. Otherwise an empty list )
    */
    public List<String> validatePassword(String password, String passwordConfirm) {
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

        return errors;
    }

    /**
    *   Validates email
    *
    *   @param email
    *   @return  a List<String> of errors ( if any. Otherwise an empty list )
    */
    public List<String> validateEmail(String email) {
        List<String> errors = new ArrayList<String>();

        if(email.length() < Config.Account.minEmailLength)
        	errors.add("err#len");

        String regexPattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
                            + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

        Pattern p = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(email);

        if (!m.find())
            errors.add("err#inv");

        return errors;
    }
}
