package com.developer.adminImpl;

import java.util.List;

import com.developer.dto.FeeDto;
import com.developer.dto.SingleStudentDto;
import com.developer.dto.SingleTeacherDto;
import com.developer.dto.StudentDto;
import com.developer.dto.StudentLeaveDto;
import com.developer.dto.TeacherDto;


public interface AdminService {

	StudentDto postStudent(StudentDto studentDto);

	List<StudentDto> getAllStudents();

	void deleteStudent(Long studentId);

	SingleStudentDto getStudentById(Long studentId);

	StudentDto updateStudent(Long studentId, StudentDto studentDto);

	FeeDto payFee(Long studentId, FeeDto feeDto);

	List<StudentLeaveDto> getAllapplyedLeave();

	StudentLeaveDto changeLeaveStatus(Long leaveId, String status);

	TeacherDto addTeacher(TeacherDto teacherDto);

	List<TeacherDto> getAllTeachers();

	void deleteTeacher(Long teacherId);


	SingleTeacherDto getTeacherById(Long teacherId);

	TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto);

}
