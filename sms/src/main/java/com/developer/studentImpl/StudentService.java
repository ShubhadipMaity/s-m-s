package com.developer.studentImpl;

import java.util.List;

import com.developer.dto.SingleStudentDto;
import com.developer.dto.StudentDto;
import com.developer.dto.StudentLeaveDto;

public interface StudentService {


	SingleStudentDto getStudentById(Long studentId);


	StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto);

	List<StudentLeaveDto> getAllapplyLeaveId(Long studentId);


	StudentDto updateStudent(Long studentId, StudentDto studentDto);

}
