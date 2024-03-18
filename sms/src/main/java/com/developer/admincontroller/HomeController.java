package com.developer.admincontroller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.developer.dto.TeacherDto;
import com.developer.home.HomeService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HomeController {
	
	private final HomeService homeService;

	
	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllTeachers() {
		List<TeacherDto> allTeacher = homeService.getAllTeachers();
		return ResponseEntity.ok(allTeacher);
	}

}
