package com.voter.registration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.voter.registration.entity.User;
import com.voter.registration.entity.Voter;
import com.voter.registration.payloads.VoterDto;
import com.voter.registration.repository.UserRepository;
import com.voter.registration.service.VoterService;

@RestController
@RequestMapping("/public")
public class VoterController {

	@Autowired
	private VoterService voterService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/voters")
	public ResponseEntity<Object> registerVoter(@RequestBody VoterDto voterDto){
		try {
		VoterDto registerVoter = voterService.registerVoter(voterDto);
		
		return new ResponseEntity<Object>(registerVoter, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/voters")
	public ResponseEntity<List<Voter>> getAllVoters(){
		List<Voter> allVoter = voterService.fetchAllVoter();
		return new ResponseEntity<List<Voter>>(allVoter, HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> list = userRepository.findAll();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
}
