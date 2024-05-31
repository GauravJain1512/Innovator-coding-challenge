package com.voter.registration.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.voter.registration.entity.Voter;
import com.voter.registration.payloads.VoterDto;


public interface VoterService {

	public VoterDto registerVoter(VoterDto voterDto);
	
	public List<Voter> fetchAllVoter();
}
