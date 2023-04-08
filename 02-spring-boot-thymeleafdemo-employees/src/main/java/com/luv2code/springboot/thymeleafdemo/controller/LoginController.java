package com.luv2code.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		// return "plain-login";
		return "fancy-login";
	}
	
	// add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "access-denied";
	}
	
//	@GetMapping("/error")
//	public String errorPage() {
//		return "error";
//	}
	
}
