package com.developer.studentImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.developer.dto.SingleStudentDto;
import com.developer.dto.StudentDto;
import com.developer.dto.StudentLeaveDto;
import com.developer.entites.StudentLeave;
import com.developer.entites.User;
import com.developer.enums.StudentLeaveStatus;
import com.developer.repository.StudentLeaveRepository;
import com.developer.repository.UserRepositiry;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final UserRepositiry userRepositiry;

	private final StudentLeaveRepository studentLeaveRepository;

	@Override
	public SingleStudentDto getStudentById(Long studentId) {

		Optional<User> optionalUser = userRepositiry.findById(studentId);
		SingleStudentDto singleStudentDto = new SingleStudentDto();
		optionalUser.ifPresent(user -> singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;

	}

	@Override
	public StudentLeaveDto applyLeave(StudentLeaveDto studentLeaveDto) {
		Optional<User> optionalUser = userRepositiry.findById(studentLeaveDto.getUserid());
		if (optionalUser.isPresent()) {
			StudentLeave studentLeave = new StudentLeave();
			studentLeave.setSubject(studentLeaveDto.getSubject());
			studentLeave.setBody(studentLeaveDto.getBody());
			studentLeave.setDate(new Date());
			studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Pending);
			studentLeave.setUser(optionalUser.get());
			StudentLeave submittedStudentLeave = studentLeaveRepository.save(studentLeave);
			StudentLeaveDto studentLeaveDto1 = new StudentLeaveDto();

			studentLeaveDto1.setId(submittedStudentLeave.getId());
			return studentLeaveDto1;
		}
		return null;
	}

	@Override
	public List<StudentLeaveDto> getAllapplyLeaveId(Long studentId) {
		return studentLeaveRepository.findAllByUserId(studentId).stream().map(StudentLeave::getStudentLeaveDto).collect(Collectors.toList());
	}

	@Override
	public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
		
		Optional<User> optionalUser = userRepositiry.findById(studentId);
		
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			user.setName(studentDto.getName());
			user.setEmail(studentDto.getEmail());
			user.setPassword(studentDto.getPassword());
			user.setFatherName(studentDto.getFatherName());
  		    user.setMotherName(studentDto.getMotherName());
			user.setStudentClass(studentDto.getStudentClass());
			user.setDob(studentDto.getDob());
			user.setAddress(studentDto.getAddress());
			user.setGender(studentDto.getGender());
			

//			BeanUtils.copyProperties(studentDto, user);
			
			
			User updatedStudent = userRepositiry.save(user);
			StudentDto updateStudentDto = new StudentDto();
			updateStudentDto.setId(updatedStudent.getId());

			return updateStudentDto;
		}
		return null;
	}

}
