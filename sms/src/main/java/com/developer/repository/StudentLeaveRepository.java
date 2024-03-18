package com.developer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developer.entites.StudentLeave;


public interface StudentLeaveRepository extends JpaRepository<StudentLeave, Long>{

	List<StudentLeave> findAllByUserId(Long studentId);

}
