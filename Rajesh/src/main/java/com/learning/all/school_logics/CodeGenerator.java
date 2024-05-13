package com.learning.all.school_logics;

import java.text.DecimalFormat;
import java.util.Random;



public class CodeGenerator {
	


	public static String generateUniqueCode() {

			return new DecimalFormat("0000")
					.format(new Random().nextInt(9999));
		
	}
	 
	  }
	 

