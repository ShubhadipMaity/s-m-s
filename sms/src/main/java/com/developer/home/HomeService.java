package com.developer.home;

import org.springframework.stereotype.Service;

import com.developer.dto.TeacherDto;
import com.developer.entites.Teacher;
import com.developer.repository.TeacherRepository;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeService {

	private final TeacherRepository teacherRepository;
	
	public List<TeacherDto> getAllTeacher(){
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
		
	}

	public List<TeacherDto> getAllTeachers() {
		// TODO Auto-generated method stub
		return null;
	}
}
