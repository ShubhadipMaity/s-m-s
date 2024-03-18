package com.developer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.developer.entites.User;
import com.developer.enums.UserRole;

@Repository
public interface UserRepositiry extends JpaRepository<User, Long>{


	User findByRole(UserRole userRole);

	Optional<User> findFirstByEmail(String email);

	Optional<User> findAllByRole(UserRole student);


}
