package com.voter.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voter.registration.config.CustomUserDetailsService;
import com.voter.registration.config.JwtTokenHelper;
import com.voter.registration.entity.User;
import com.voter.registration.payloads.LoginDto;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginDto user){
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
			Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticate.getName());
			String token = jwtTokenHelper.generateToken(userDetails);
			return new ResponseEntity<Object>(token, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>("Bad Credentials", HttpStatus.BAD_REQUEST);
		}
	}

}
