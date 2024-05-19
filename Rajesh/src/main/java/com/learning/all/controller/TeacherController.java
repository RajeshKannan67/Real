package com.learning.all.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.zxing.WriterException;
import com.learning.all.entity.SignupEntity;
import com.learning.all.entity.StudentEntity;
import com.learning.all.school_logics.School_Services;

@Controller
public class TeacherController {

	@Autowired
	private School_Services sclServices;
	
	
	@GetMapping("/teacher/home")
	public String teascherHome() {
		return "Teacher-home";
	}
	
	
	
	
	@PostMapping("/teacher/id")
	 public String findStudentById(@RequestParam("studentId") Long studentId, Model model) throws IOException, WriterException {
	     
		 BufferedImage qrCodeImage = sclServices.findById(studentId);
	    
		 String qrCodeImageBase64 = sclServices.convertImageToBase64(qrCodeImage);
	     
	     //System.out.println("Base64 Image Data: " + qrCodeImageBase64);
	     
	     model.addAttribute("qrCodeImageBase64", qrCodeImageBase64);
	     
	     return "image";
	 }
	
	
	@GetMapping("/studentDetail")
	public String allStudent (Model model){
		List<StudentEntity> findAll = sclServices.findAll();
		model.addAttribute("students", findAll);
		return "studentDetail";
	}
	
	
}
