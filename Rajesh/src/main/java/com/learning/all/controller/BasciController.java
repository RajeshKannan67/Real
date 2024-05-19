package com.learning.all.controller;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.WriterException;
import com.learning.all.entity.SignupEntity;
import com.learning.all.entity.StudentEntity;
import com.learning.all.entity.UserDetails;
import com.learning.all.school_logics.School_Services;

import reactor.core.publisher.Mono;

@Controller
public class BasciController {

	@Autowired
	private School_Services sclServices;
	
	 
	@GetMapping("/home")
	public String home() {
		
        return "home";
		
	}
	
	 @GetMapping("/signup")
	    public String signup() {
		 
	        return "signup";
	    }
	 
	 @PostMapping("/siginingUp")
	 public String sigingUp(@ModelAttribute("signupForm") SignupEntity signupForm, BindingResult bindingResult ,Model model) {
	        if (bindingResult.hasErrors()) {
	            return "redirect:/siginingUp";
	        }
	        try {
	        	
	        	SignupEntity signupEntity =  new SignupEntity();
	        	signupEntity = sclServices.processSignup(signupForm);
		        model.addAttribute("signupEntity", signupEntity);

			} catch (Exception e) {
				System.err.println("Error adding in records");
				e.printStackTrace();
			}
	        return "credentials";
	    }
	 
	 
	
	 
	 
	 
//	 @GetMapping("primary/success")
//	    public String successPage() {
//	        return "success"; 
//	    }
//	 
	 @GetMapping("/login")
	    public String login() {
		 System.out.println("----------------------> Login Page called up");
	        return "login";
	    }
	 
	 @PostMapping("/userInfo")
	 public String user_Info(@ModelAttribute("allrecords") SignupEntity user, BindingResult bindingResult ,Model model) {
		 System.err.println("------User added in the UserInfo Table------");
		 if (bindingResult.hasErrors()) {
	            return "redirect:/credentials";
	        }
	        try {
	        	sclServices.addUser(user);
			} catch (Exception e) {
				System.err.println("Error adding in records");
				e.printStackTrace();
			}
	        return "redirect:/login";

	    }
	 
//	 @PostMapping("/log")
//	 public String login(@ModelAttribute("logDetails") UserDetails details, Model model) {
//		 	System.out.println("UserId ->"+details.getUserId());
//			System.out.println("Pass ->" +details.getPassword());
//		    boolean result = sclServices.authenticate(details);
//		    if (result) {
//		        System.out.println("Pass");
//		        return "redirect:https://www.iplt20.com/";
//		    } else {
//		        System.out.println("fail");
//		        String msg = "Authentication Failed...!";
//		        model.addAttribute("errorMessage", msg);
//		        return "redirect:/login";
//		    }
//	 
//}
	 
}
