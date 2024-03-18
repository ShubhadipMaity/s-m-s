package com.developer.admincontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developer.adminImpl.AdminService;
import com.developer.dto.FeeDto;
import com.developer.dto.SingleStudentDto;
import com.developer.dto.SingleTeacherDto;
import com.developer.dto.StudentDto;
import com.developer.dto.StudentLeaveDto;
import com.developer.dto.TeacherDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authenticate/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/student")
	public ResponseEntity<?> addStudent(@RequestBody StudentDto studentDto) {
		StudentDto createStudentDto = adminService.postStudent(studentDto);

		if (createStudentDto == null) {
			return new ResponseEntity<>("something went wrong.", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createStudentDto);
	}

	@GetMapping("/students")
	public ResponseEntity<List<StudentDto>> getAllStudents() {
		List<StudentDto> allStudent = adminService.getAllStudents();

		return ResponseEntity.ok(allStudent);
	}

	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long studentId) {
		adminService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<SingleStudentDto> getStudentById(@PathVariable Long studentId) {
		SingleStudentDto singleStudentDto = adminService.getStudentById(studentId);
		if (singleStudentDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleStudentDto);

	}

	@PutMapping("/student/{studentId}")
	public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody StudentDto studentDto) {
		StudentDto createStudentDto = adminService.updateStudent(studentId, studentDto);
		if (createStudentDto == null)
			return new ResponseEntity<>("Something want wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(createStudentDto);
	}

	@PostMapping("/fee/{studentId}")
	public ResponseEntity<?> payFee(@PathVariable Long studentId, @RequestBody FeeDto feeDto) {
		FeeDto paidfeeDto = adminService.payFee(studentId, feeDto);

		if (paidfeeDto == null) {
			return new ResponseEntity<>("something went wrong.", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(paidfeeDto);
	}

	@GetMapping("/leaves")
	public ResponseEntity<List<StudentLeaveDto>> getAllapplyedLeave() {
		List<StudentLeaveDto> studentLeaveDtos = adminService.getAllapplyedLeave();
		if (studentLeaveDtos == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(studentLeaveDtos);
	}

	@GetMapping("/leave/{leaveId}/{status}")
	public ResponseEntity<?> changeLeaveStatus(@PathVariable Long leaveId, @PathVariable String status) {
		StudentLeaveDto studentLeaveDtos = adminService.changeLeaveStatus(leaveId, status);
		if (studentLeaveDtos == null)
			return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.ok(studentLeaveDtos);
	}

	// Teachers Operations

	@PostMapping("/teacher")
	public ResponseEntity<?> addTeacher(@RequestBody TeacherDto teacherDto) {
		TeacherDto createTeacherDto = adminService.addTeacher(teacherDto);
		if (createTeacherDto == null) {
			return new ResponseEntity<>("something went wrong.", HttpStatus.BAD_REQUEST);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createTeacherDto);
	}

	@GetMapping("/teachers")
	public ResponseEntity<List<TeacherDto>> getAllTeachers() {
		List<TeacherDto> allTeacher = adminService.getAllTeachers();
		return ResponseEntity.ok(allTeacher);
	}

	@DeleteMapping("/teacher/{teacherId}")
	public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) {
		adminService.deleteTeacher(teacherId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<SingleTeacherDto> getTeacherById(@PathVariable Long teacherId) {
		SingleTeacherDto singleTeacherDto = adminService.getTeacherById(teacherId);
		if (singleTeacherDto == null)
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(singleTeacherDto);

	}

	@PutMapping("/teacher/{teacherId}")
	public ResponseEntity<?> updateTeacher(@PathVariable Long teacherId, @RequestBody TeacherDto teacherDto) {
		TeacherDto updatedTeacherDto = adminService.updateTeacher(teacherId, teacherDto);
		if (updatedTeacherDto == null)
			return new ResponseEntity<>("Something want wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.OK).body(updatedTeacherDto);
	}
	
	

}
