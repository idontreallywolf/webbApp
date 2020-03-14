package com.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webapp.Config;

@Controller
public class PageController {
	
	/*
		@GetMapping("/pageSample")
		public String hello(@RequestParam(name="user", required=false, defaultValue="User") String user, Model model) {
			
			// Default attributes
			model = initDefaultAttributes("PageTitle", "cssFileName", "pageSample", model);
			
			// extra attributes
			model.addAttribute("user", user);
			
			return init(model);
		}
	*/
	
	/*
	 *  CONTACT PAGE HANDLER
	 */
	@GetMapping("/contact")
	public String contact(Model model) {
		model = initDefaultAttributes("Contact Us", null, "contact", model);
		return index(model);
	}
	
	/*
	 *  ABOUT PAGE HANDLER
	 */
	@GetMapping("/about")
	public String about(Model model) {
		model = initDefaultAttributes("About", null, "about", model);
		return index(model);
	}

	/*
	 *  ABOUT PAGE HANDLER
	 */
	@GetMapping("/main")
	public String main(Model model) {
		model = initDefaultAttributes("Home", null, "main", model);
		return index(model);
	}
	
	/*
	 *  INDEX PAGE HANDLER
	 */
	@GetMapping("/")
	public String index(Model model) {
		System.out.println("Accessing index page");
		
		// Set default title if empty.
		if(model.getAttribute("title") == null)
			model.addAttribute("title", "Welcome | Pepe");
		
		// Set default page 'main' if empty.
		if(model.getAttribute("page") == null)
			model.addAttribute("page", "main");
		
		// default css path
		model.addAttribute("defaultCss", "/css/default.css");
		
		return "index";
	}
	
	/**
		@param String title - page title *
		@param String cssFileName - (OPTIONAL) name of the css file which belongs to the specific page
		@param String page - page name ( same as &lt;pageName&gt;.html file )
		@param Model m
		
		@return the Model after setting necessary attributes.
	*/
	public Model initDefaultAttributes(String title, String cssFileName, String page, Model m) {
		m.addAttribute("title", Config.mainTitle+" | "+title);
		m.addAttribute("cssPath", (cssFileName == null ? null:"/css/"+cssFileName));
		m.addAttribute("page", page);
		return m;
	}
}
