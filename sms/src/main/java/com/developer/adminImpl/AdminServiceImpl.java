package com.developer.adminImpl;

import java.util.Date;
import java.util.Objects;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.developer.dto.FeeDto;
import com.developer.dto.SingleStudentDto;
import com.developer.dto.SingleTeacherDto;
import com.developer.dto.StudentDto;
import com.developer.dto.StudentLeaveDto;
import com.developer.dto.TeacherDto;
import com.developer.entites.Fee;
import com.developer.entites.StudentLeave;
import com.developer.entites.Teacher;
import com.developer.entites.User;
import com.developer.enums.StudentLeaveStatus;
import com.developer.enums.UserRole;
import com.developer.repository.FeeRepository;
import com.developer.repository.StudentLeaveRepository;
import com.developer.repository.TeacherRepository;
import com.developer.repository.UserRepositiry;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final UserRepositiry userRepositiry;

	private final FeeRepository feeRepository;

	private final StudentLeaveRepository studentLeaveRepository;

	private final TeacherRepository teacherRepository;

	@PostConstruct
	public void createAdminAcount() {

		User adminAccount = userRepositiry.findByRole(UserRole.ADMIN);

		if (adminAccount == null) {
			User admin = new User();
			admin.setEmail("admin@test.com");
			admin.setName("admin");
			admin.setRole(UserRole.ADMIN);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepositiry.save(admin);

		}

	}

	@Override
	public StudentDto postStudent(StudentDto studentDto) {

		Optional<User> optionalUser = userRepositiry.findFirstByEmail(studentDto.getEmail());
		if (optionalUser.isEmpty()) {

			User user = new User();
			BeanUtils.copyProperties(studentDto, user);
			user.setPassword(new BCryptPasswordEncoder().encode(studentDto.getPassword()));
			user.setRole(UserRole.STUDENT);
			User createdUser = userRepositiry.save(user);

			StudentDto cretedStudentDto = new StudentDto();

			cretedStudentDto.setId(createdUser.getId());
			cretedStudentDto.setEmail(createdUser.getEmail());

			return cretedStudentDto;
		}
		return null;
	}

	@Override
	public List<StudentDto> getAllStudents() {

		return userRepositiry.findAllByRole(UserRole.STUDENT).stream().map(User::getStudentDto)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteStudent(Long studentId) {
		userRepositiry.deleteById(studentId);
	}

	@Override
	public SingleStudentDto getStudentById(Long studentId) {
		Optional<User> optionalUser = userRepositiry.findById(studentId);
		SingleStudentDto singleStudentDto = new SingleStudentDto();
		optionalUser.ifPresent(user -> singleStudentDto.setStudentDto(user.getStudentDto()));
		return singleStudentDto;

	}

	@Override
	public StudentDto updateStudent(Long studentId, StudentDto studentDto) {
		Optional<User> optionalUser = userRepositiry.findById(studentId);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

//			user.setName(studentDto.getName());
//			user.setEmail(studentDto.getEmail());
//			user.setPassword(studentDto.getPassword());
//			user.setFathername(studentDto.getFathername());
//			user.setMothername(studentDto.getMothername());
//			user.setStudentlass(studentDto.getStudentlass());
//			user.setDob(studentDto.getDob());
//			user.setAddress(studentDto.getAddress());
//			user.setGender(studentDto.getGender());

			BeanUtils.copyProperties(studentDto, user);

			User updatedStudent = userRepositiry.save(user);
			StudentDto updateStudentDto = new StudentDto();
			updateStudentDto.setId(updatedStudent.getId());

			return updateStudentDto;
		}
		return null;
	}

	@Override
	public FeeDto payFee(Long studentId, FeeDto feeDto) {
		Optional<User> optionalStudent = userRepositiry.findById(studentId);
		if (optionalStudent.isPresent()) {
			Fee fee = new Fee();
			fee.setAmount(feeDto.getAmount());
			fee.setMonth(feeDto.getMonth());
			fee.setDescription(feeDto.getDescription());
			fee.setCreateDate(new Date());
			fee.setGivenBy(feeDto.getGivenBy());
			fee.setUser(optionalStudent.get());

			Fee paidFee = feeRepository.save(fee);

			FeeDto paidfeeDto = new FeeDto();
			paidfeeDto.setId(paidFee.getId());

			return paidfeeDto;

		}
		return null;
	}

	@Override
	public List<StudentLeaveDto> getAllapplyedLeave() {

		return studentLeaveRepository.findAll().stream().map(StudentLeave::getStudentLeaveDto)
				.collect(Collectors.toList());
	}

	@Override
	public StudentLeaveDto changeLeaveStatus(Long leaveId, String status) {
		Optional<StudentLeave> optionalStudentLeave = studentLeaveRepository.findById(leaveId);
		if (optionalStudentLeave.isPresent()) {
			StudentLeave studentLeave = optionalStudentLeave.get();
			if (Objects.equals(status, "Approve")) {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Approved);
			} else {
				studentLeave.setStudentLeaveStatus(StudentLeaveStatus.Disapproved);
			}
			StudentLeave updatedStudentLeave = studentLeaveRepository.save(studentLeave);
			StudentLeaveDto updatedStudentLeaveDto = new StudentLeaveDto();
			updatedStudentLeaveDto.setId(updatedStudentLeave.getId());

			return updatedStudentLeaveDto;
		}
		return null;
	}

	@Override
	public TeacherDto addTeacher(TeacherDto teacherDto) {
		Teacher teacher = new Teacher();
		BeanUtils.copyProperties(teacherDto, teacher);
		Teacher createdTeacher = teacherRepository.save(teacher);
		TeacherDto createdTeacherDto = new TeacherDto();
		createdTeacherDto.setId(createdTeacher.getId());
		return createdTeacherDto;
	}

	@Override
	public List<TeacherDto> getAllTeachers() {
		return teacherRepository.findAll().stream().map(Teacher::getTeacherDto).collect(Collectors.toList());
	}

	@Override
	public void deleteTeacher(Long teacherId) {
		teacherRepository.deleteById(teacherId);
	}

	@Override
	public SingleTeacherDto getTeacherById(Long teacherId) {

		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
		if (optionalTeacher.isPresent()) {
			SingleTeacherDto singleTeacherDto = new SingleTeacherDto();
			singleTeacherDto.setTeacherDto(optionalTeacher.get().getTeacherDto());
			return singleTeacherDto;
		}
		return null;
	}

	@Override
	public TeacherDto updateTeacher(Long teacherId, TeacherDto teacherDto) {
		Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
		if (optionalTeacher.isPresent()) {
			Teacher updateTeacher = optionalTeacher.get();
			updateTeacher.setName(teacherDto.getName());
			updateTeacher.setAddress(teacherDto.getAddress());
			updateTeacher.setDepartment(teacherDto.getDepartment());
			updateTeacher.setDob(teacherDto.getDob());
			updateTeacher.setGender(teacherDto.getGender());
			updateTeacher.setQualification(teacherDto.getQualification());

			Teacher updatedTeacher = teacherRepository.save(updateTeacher);

			TeacherDto updatedTeacherDto = new TeacherDto();
			updatedTeacher.setId(updatedTeacher.getId());
			return updatedTeacherDto;
		}
		return null;
	}

}
