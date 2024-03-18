package com.developer.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.developer.entites.User;
import com.developer.repository.UserRepositiry;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService{
	
	
	private final UserRepositiry userRepositiry;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		// Write a logic to User get from DB

		Optional<User> userOptional = userRepositiry.findFirstByEmail(email);
		if (userOptional.isEmpty())
			throw new UsernameNotFoundException("Username not found", null);
		return new org.springframework.security.core.userdetails.User(userOptional.get().getEmail(),
				userOptional.get().getPassword(), new ArrayList<>());

	}

}
