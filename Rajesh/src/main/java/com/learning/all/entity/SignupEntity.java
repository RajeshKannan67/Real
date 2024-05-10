package com.learning.all.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupEntity {

	
	
	    @NotBlank
	    private String name;
	    @NotBlank
	    private String place;
	    @NotBlank
	    private String school;
	    @NotBlank
	    private int age;
	    @NotBlank
	    private String role;
	    @NotBlank
	    private String password;
	    @NotBlank
	    private String confirmPsd;
	    @NotBlank
	    private String userId;
	    @NotBlank
	    private Long phnumber;
}
