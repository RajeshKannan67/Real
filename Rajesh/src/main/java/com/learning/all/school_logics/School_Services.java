package com.learning.all.school_logics;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.encoder.QRCode;
import com.learning.all.entity.PrincipalEntity;
import com.learning.all.entity.SignupEntity;
import com.learning.all.entity.StudentEntity;
import com.learning.all.entity.TeacherEntity;
import com.learning.all.entity.UserDetails;
import com.learning.all.qr.service.QRCodeGenerator;
import com.learning.all.repositries.PrincipalRepo;
import com.learning.all.repositries.StudentRepo;
import com.learning.all.repositries.TeacherRepo;
import com.learning.all.repositries.UserRepo;
import com.learning.all.twilio.config.TwilioConfig;
import com.learning.all.twilio.dto.OtpStatus;
import com.learning.all.twilio.dto.PasswordResetResponseDto;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class School_Services {

	@Autowired
	private StudentRepo studentRepo;
	@Autowired
	private TeacherRepo teacherRepo;
	@Autowired
	private PrincipalRepo principalRepo;
	@Autowired
	public UserRepo repo;
	@Autowired
	private TwilioConfig twilioConfig;

	public SignupEntity processSignup(SignupEntity signupForm) throws Exception {

		String generatedCode = null;
		
		boolean status = true;
		if (signupForm.getRole().equalsIgnoreCase("student")) {
			StudentEntity student = new StudentEntity();
			student.setName(signupForm.getName());
			student.setAge(signupForm.getAge());
			student.setPlace(signupForm.getPlace());
			student.setSchool(signupForm.getSchool());
			student.setPhnumber(signupForm.getPhnumber());
			student.setRole("STUDENT");
			for (; status;) {

				System.out.println("-------------------");
				generatedCode = CodeGenerator.generateUniqueCode();
				Long valueOf = Long.valueOf(generatedCode);
				String codeUnique = isCodeUnique(valueOf);
				System.out.println("codeUnique " + codeUnique);

				if (codeUnique.equalsIgnoreCase("newUser")) {
					System.out.println("It's a New User ");
					student.setUId(generatedCode);
					status = false;
					signupForm.setUserId(generatedCode);
					//messagetoPrincipal(signupForm); // Intimation message will send to principal
					break;
				} else {
					System.out.println(generatedCode + "===========================");
					System.out.println("Already existing");
					status = true;
				}
			}

			studentRepo.save(student);

		}

		else if (signupForm.getRole().equalsIgnoreCase("teacher")) {
			TeacherEntity teacher = new TeacherEntity();
			teacher.setName(signupForm.getName());
			teacher.setAge(signupForm.getAge());
			teacher.setPlace(signupForm.getPlace());
			teacher.setSchool(signupForm.getSchool());
			teacher.setPhnumber(signupForm.getPhnumber());
			teacher.setRole("TEACHER,STUDENT");
			for (; status;) {

				System.out.println("-------------------");
				generatedCode = CodeGenerator.generateUniqueCode();
				Long valueOf = Long.valueOf(generatedCode);
				String codeUnique = isCodeUnique(valueOf);
				System.out.println("codeUnique " + codeUnique);

				if (codeUnique.equalsIgnoreCase("newUser")) {
					System.out.println("It's a New User ");
					teacher.setUId(generatedCode);
					status = false;
					signupForm.setUserId(generatedCode);
					//messagetoPrincipal(signupForm); // Intimation message will send to principal
					break;
				} else {
					System.out.println(generatedCode + "===========================");
					System.out.println("Already existing");
					status = true;
					;
				}
			}

			teacherRepo.save(teacher);

		}

		else if (signupForm.getRole().equalsIgnoreCase("principal")) {
			PrincipalEntity princi = new PrincipalEntity();
			princi.setName(signupForm.getName());
			princi.setAge(signupForm.getAge());
			princi.setPlace(signupForm.getPlace());
			princi.setSchool(signupForm.getSchool());
			princi.setPhnumber(signupForm.getPhnumber());
			princi.setRole("PRINCIPAL,TEACHER,STUDENT");
			
			for (; status;) {

				System.out.println("-------------------");
				generatedCode = CodeGenerator.generateUniqueCode();
				Long valueOf = Long.valueOf(generatedCode);
				String codeUnique = isCodeUnique(valueOf);
				System.out.println("codeUnique " + codeUnique);

				if (codeUnique.equalsIgnoreCase("newUser")) {
					System.out.println("It's a New User ");
					princi.setUId(generatedCode);
					status = false;
					break;
				} else {
					System.out.println(generatedCode + "===========================");
					System.out.println("Already existing");
					status = true;
					;
				}
			}

			principalRepo.save(princi);
		}

		else {
			System.out.println("There is a error in Adding");
		}

		signupForm.getName();
		signupForm.setUserId(generatedCode);
		signupForm.getPhnumber();
		return signupForm;
	}

	public String isCodeUnique(Long code) {
		String status = null;
		UserDetails userDetails = repo.findByUserId(code);

		if (userDetails == null) {

			status = "newUser";
		} else {

			return "oldUser";
		}

		return status;

	}

	public void addUser(SignupEntity user) {
		Long userId;
		user.getPassword();
		UserDetails userDetails = new UserDetails();

		userId = Long.parseLong(user.getUserId());
		userDetails.setUserId(userId);
		userDetails.setName(user.getName());
		userDetails.setPhnumber(user.getPhnumber());
		String plainPassword = user.getPassword();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(plainPassword);
		userDetails.setPassword(hashedPassword);
		userDetails.setSchool(user.getSchool());
		if(user.getRole().equalsIgnoreCase("principal")) {
			userDetails.setRole("PRINCIPAL,TEACHER,STUDENT");
		}else if(user.getRole().equalsIgnoreCase("teacher")) {
			userDetails.setRole("TEACHER,STUDENT");
		}else {
			userDetails.setRole("STUDENT");
		}
		repo.save(userDetails);

	}

	public boolean authenticate(UserDetails details) {
		boolean status = false;
		Long user_id = details.getUserId();
		String providedPassword = details.getPassword();
		System.out.println("UserId ->"+details.getUserId());
		System.out.println("Pass ->" +details.getPassword());
		UserDetails user = repo.findByUserId(user_id);
		if (user != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			if (passwordEncoder.matches(providedPassword, user.getPassword())) {

				status = true;
				return status;
			} else {
				status = false;
				return status;
			}

		}
		return status;
	}

	public void messagetoPrincipal(SignupEntity user) {
		PasswordResetResponseDto status = null;
		
		if (user != null) {

			try {
				System.out.println("Service Calling");
				PhoneNumber to = new PhoneNumber("+91 8056883323");
				PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
				String userDetails = String.format("Dear Sir,\n" +
                        "Today a new user is created:\n" +
                        "User ID: %s\n" +
                        "Name: %s\n" +
                        "Age: %s\n" +
                        "Phone No: %s\n" +
                        "Role: %s\n" +
                        "Thank You..!",
                        user.getUserId(),
                        user.getName(),
                        user.getAge(),
                        user.getPhnumber(),
                        user.getRole().toUpperCase());
				System.out.println(userDetails);
				Message message = Message.creator(to, from, userDetails).create();
				status = new PasswordResetResponseDto(OtpStatus.DELIVERED, userDetails);
			} catch (Exception ex) {
				status = new PasswordResetResponseDto(OtpStatus.FAILED, ex.getMessage());
			}
		}

	}

	public BufferedImage findById(Long id) throws IOException, WriterException {

		BufferedImage code = null;
		System.out.println("id ->"+id);
		UserDetails user = repo.findByUserId(id);
		
		if (user!=null) 
		code = QRCodeGenerator.generateQRCode(user);
		else
			System.out.println("No user in this "+id+"..!");
		
		return code;
	}

	public String convertImageToBase64(BufferedImage image) throws IOException {
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        ImageIO.write(image, "png", outputStream);
	        byte[] imageBytes = outputStream.toByteArray();
	        return Base64.getEncoder().encodeToString(imageBytes);
	    }

	public List<StudentEntity> findAll() {

		List<StudentEntity> findAll = studentRepo.findAll();
		
		return findAll;
		
	}

	public Boolean deleteByStudentId(Long studentId) {

		Optional<StudentEntity> student = studentRepo.findById(studentId);
		Optional<UserDetails> userInfo =         repo.findById(studentId);
		if(student.isPresent() && userInfo.isPresent()) {
			
			studentRepo.deleteById(studentId);
			       repo.deleteById(studentId);
			  
			return true;
			
		}else return false;
		
	
		
	}

}
