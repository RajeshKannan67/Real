package com.learning.all.school_logics;

import java.util.Random;



public class CodeGenerator {
	


	public  String generateUniqueCode() {

		Random random = new Random();
		String code = null;
		boolean status = true;
		
//		do {
//			int randomNumber = random.nextInt(10000);
//			code = String.format("%04d", randomNumber);
//			System.out.println("code "+code);
//		} while (!isCodeUnique(code));
	
			
		int randomNumber = random.nextInt(10000);
		code = String.format("%04d", randomNumber);
		return code;
	}

	
	 
	  }
	 

