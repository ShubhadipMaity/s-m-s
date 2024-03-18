package com.developer.entites;

import java.util.Date;

import com.developer.dto.TeacherDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "teachers")
public class Teacher {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String gender;
	
	private String department;
	
	private String qualification;
	
	private Date dob;
	
	private String address;

	
	public TeacherDto getTeacherDto() {
		TeacherDto teacherDto=new TeacherDto();
		teacherDto.setId(id);
		teacherDto.setName(name);
		teacherDto.setGender(gender);
		teacherDto.setDepartment(department);
		teacherDto.setQualification(qualification);
		teacherDto.setDob(dob);
		teacherDto.setAddress(address);
		
		return teacherDto;
		
	}
}
