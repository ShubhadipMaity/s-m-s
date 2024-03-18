package com.developer.admincontroller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.developer.dto.AuthenticationRequest;
import com.developer.dto.AuthenticationResponse;
import com.developer.entites.User;
import com.developer.repository.UserRepositiry;
import com.developer.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;

	private final UserDetailsService userDetailsService;

	private final JwtUtil jwtUtil;

	private final UserRepositiry userRepositiry;

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

	@PostMapping("/authenticate")
	public AuthenticationResponse createAuthenticationToken(
			@RequestBody(required = true) AuthenticationRequest authenticationRequest, HttpServletResponse response)
			throws IOException {

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect Username or Password");
		} catch (DisabledException disabledException) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not Created");

			return null;
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt = jwtUtil.getGeneratedToken(userDetails.getUsername());

		Optional<User> optionalUser = userRepositiry.findFirstByEmail(userDetails.getUsername());

		var authenticationResponse = new AuthenticationResponse();

		if (optionalUser.isPresent()) {
			
			authenticationResponse.setJwtToken(jwt);
			authenticationResponse.setUserRole(optionalUser.get().getRole());
			authenticationResponse.setUserId(optionalUser.get().getId());



		}

		response.setHeader("Access-Control-Expose-Headers", "Authorization");
		response.setHeader("Access-Control-Allow-Headers",
				"Authorization,X-Pingother,Origin,X-Requested-With,Content-Type,Accept,X-Custom-header");
		response.setHeader(TOKEN_PREFIX,HEADER_STRING +jwt);

		return authenticationResponse;

	}
}
