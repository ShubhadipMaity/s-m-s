package com.developer.entites;

import java.sql.Date;

import com.developer.dto.StudentDto;
import com.developer.enums.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;



@Entity
@Data
@Table(name = "users",  uniqueConstraints=
@UniqueConstraint(columnNames={"id", "email"})

)

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name ="role")
	private UserRole role;
	
	
	public StudentDto getStudentDto() {
		StudentDto studentDto=new StudentDto();
		studentDto.setId(id);
		studentDto.setName(name);
		studentDto.setEmail(email);
		studentDto.setAddress(address);
		studentDto.setDob(dob);
		studentDto.setStudentClass(studentClass);
		studentDto.setGender(gender);
		studentDto.setFatherName(fatherName);
		studentDto.setMotherName(motherName);
		
		return studentDto;
	}
}
