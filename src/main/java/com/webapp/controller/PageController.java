package com.webapp.controller;

import org.springframework.web.servlet.ModelAndView;

import com.webapp.Config;

public class PageController {
	/**
		@param String title - page title *
		@param String cssFileName - (OPTIONAL) name of the css file which belongs to the specific page
		@param String page - page name ( same as &lt;pageName&gt;.html file )
		@param Model m
	
		@return the Model after setting necessary attributes.
	*/
	public ModelAndView initDefaultAttributes(String title, String cssFileName, String page, ModelAndView m) {
		m.addObject("title", Config.mainTitle+" | "+title);
		m.addObject("cssPath", (cssFileName == null ? null:"/css/"+cssFileName));
		m.addObject("defaultCss", "/css/default.css");
		m.addObject("page", page);
		return m;
	}
	
	/**
	 * 
	 */
	public class ResponseObject {
		private String response;
		
		public ResponseObject(String response) {
			setResponse(response);
		}
		
		public void setResponse(String response) {
			this.response = response;
		}
		
		public String getResponse() {
			return this.response;
		}
	}
}
