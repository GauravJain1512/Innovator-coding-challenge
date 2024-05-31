package com.auth.twelth.nov.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {
	
	@GetMapping("/admin/test")
	public ResponseEntity<Object> admin(@AuthenticationPrincipal UserDetails userDetails, Principal principal){
		System.out.println(userDetails.getUsername());
		System.out.println(principal.getName());
		return new ResponseEntity<Object>("Admin", HttpStatus.OK);
	}
	
	@GetMapping("/normal/test")
	public ResponseEntity<Object> normal(){
		return new ResponseEntity<Object>("normal", HttpStatus.OK);
	}
	
	@GetMapping("/both/test")
	public ResponseEntity<Object> both(){
		return new ResponseEntity<Object>("both", HttpStatus.OK);
	}

}
