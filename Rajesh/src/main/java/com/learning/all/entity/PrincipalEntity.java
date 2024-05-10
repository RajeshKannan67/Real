package com.learning.all.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table( name="principal")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PrincipalEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String uId;
	
	private String name;
	
	private String place;
	
	private String school;
	
	private int age;
	
	private Long phnumber;
}
