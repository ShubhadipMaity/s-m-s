package com.developer.dto;

import java.io.Serializable;

import com.developer.enums.UserRole;




import lombok.Data;

@Data
//@AllArgsConstructor
public class AuthenticationResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private String jwtToken;
	
	
	private UserRole userRole;
	
	private Long userId;
	


}
