package com.developer.dto;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
public class StudentDto {

	@Column(name = "id")
	private Long id;

	@Column(name = "sname")
	private String name;
	
	@Column(name = "semail")
	private String email;
	
	@Column(name = "spassowrd")
	private String password;
	
	@Column(name = "fname")
	private String fatherName;
	
	@Column(name = "mname")
	private String motherName;
	
	@Column(name = "sclass")
	private String studentClass;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "gender")
	private String gender;

}
