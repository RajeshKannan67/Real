package com.learning.all.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.learning.all.school_logics.School_Services;


@Controller
public class PrincipalController {

	
	@Autowired
	private School_Services sclServices;
	
	
	@GetMapping("/principal/home")
	public String teascherHome() {
		return "principal-home";
	}
}
