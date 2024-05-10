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
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.WriterException;
import com.learning.all.entity.SignupEntity;
import com.learning.all.entity.StudentEntity;
import com.learning.all.entity.UserDetails;
import com.learning.all.school_logics.School_Services;

import reactor.core.publisher.Mono;

@Controller
public class LoginController {

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
	 
	 @PostMapping("/sigingUp")
	 public String sigingUp(@ModelAttribute("signupForm") SignupEntity signupForm, BindingResult bindingResult ,Model model) {
	        if (bindingResult.hasErrors()) {
	            return "signup";
	        }
	        try {
	        	SignupEntity all = sclServices.processSignup(signupForm);
	        	System.out.println(all.getPhnumber()+" ---------------");
	        	
		        model.addAttribute("all", all);

			} catch (Exception e) {
				System.err.println("Error adding in records");
				e.printStackTrace();
			}
	        return "credentials";
	    }
	 
	 
	
	 @PostMapping("/userInfo")
	 public String user_Info(@ModelAttribute("allrecords") SignupEntity user, BindingResult bindingResult ,Model model) {
		 if (bindingResult.hasErrors()) {
	            return "credentials";
	        }
	        try {
	        	sclServices.addUser(user);
			} catch (Exception e) {
				System.err.println("Error adding in records");
				e.printStackTrace();
			}
	        return "redirect:/success";
	    }
	 
	 
	 @GetMapping("/success")
	    public String successPage() {
	        return "success"; 
	    }
	 
	 @GetMapping("/login")
	    public String login() {
		 
	        return "login";
	    }
	 
	 @PostMapping("/log")
	 public String login(@ModelAttribute("logDetails") UserDetails details, Model model) {
		    boolean result = sclServices.authenticate(details);
		    if (result) {
		        System.out.println("Pass");
		        return "redirect:https://www.iplt20.com/";
		    } else {
		        System.out.println("fail");
		        String msg = "Authentication Failed...!";
		        model.addAttribute("errorMessage", msg);
		        return "redirect:/login";
		    }
	 
}
	 
	 
	 @PostMapping("/id")
	    public String findStudentById(@RequestParam("studentId") Long studentId, Model model) throws IOException, WriterException {
	        BufferedImage qrCodeImage = sclServices.findById(studentId);
	        String qrCodeImageBase64 = sclServices.convertImageToBase64(qrCodeImage);
	        model.addAttribute("qrCodeImageBase64", qrCodeImageBase64);
	        return "image";
	    }

	    
	 
}
