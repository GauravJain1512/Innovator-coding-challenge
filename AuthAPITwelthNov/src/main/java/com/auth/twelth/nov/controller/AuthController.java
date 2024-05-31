package com.auth.twelth.nov.controller;

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

import com.auth.twelth.nov.config.CustomUserDeatilsService;
import com.auth.twelth.nov.config.JwtTokenHelper;
import com.auth.twelth.nov.entity.User;

@RestController
@RequestMapping("/public")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired 
	private CustomUserDeatilsService customUserDeatilsService;
	
	@PostMapping("/auth")
	public ResponseEntity<Object> login(@RequestBody User user){
		try {
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
			Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
			UserDetails userDetails = customUserDeatilsService.loadUserByUsername(authenticate.getName());
			String token = jwtTokenHelper.generateToken(userDetails);
			return new ResponseEntity<Object>(token, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>("Bad Credentials", HttpStatus.BAD_REQUEST);
		}
	}

}
