package com.developer.studentcontroller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.dto.SingleStudentDto;
import com.developer.dto.StudentDto;
import com.developer.dto.StudentLeaveDto;
import com.developer.studentImpl.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/authenticate/student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;

	@GetMapping("/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId) {
		SingleStudentDto singleStudentDto = studentService.getStudentById(studentId);
		if (singleStudentDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleStudentDto);

	}

	@PostMapping("/leave")
	public ResponseEntity<?> applyLeave(@RequestBody StudentLeaveDto studentLeaveDto) {
		StudentLeaveDto submitteLeaveDto = studentService.applyLeave(studentLeaveDto);
		if (submitteLeaveDto == null)
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(submitteLeaveDto);

	}

	@GetMapping("/leave/{studentId}")
	public ResponseEntity<List<StudentLeaveDto>> getAllapplyLeaveId(@PathVariable Long studentId) {
		List<StudentLeaveDto> studentLeaveDtos = studentService.getAllapplyLeaveId(studentId);
		if (studentLeaveDtos == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(studentLeaveDtos);

	}
	
	
	@PutMapping("/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto) {
		StudentDto createStudentDto = studentService.updateStudent(studentId, studentDto);
		if (createStudentDto == null)
			return new ResponseEntity<>("Something want wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createStudentDto);
	}

	
}
