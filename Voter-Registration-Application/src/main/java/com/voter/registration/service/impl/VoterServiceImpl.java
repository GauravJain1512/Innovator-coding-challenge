package com.voter.registration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voter.registration.entity.Voter;
import com.voter.registration.payloads.VoterDto;
import com.voter.registration.repository.VoterRepository;
import com.voter.registration.service.VoterService;
@Service
public class VoterServiceImpl implements VoterService {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private VoterRepository voterRepository;

	@Override
	public VoterDto registerVoter(VoterDto voterDto) {
		
		Voter voter = modelMapper.map(voterDto, Voter.class);
		
		Voter saverVoter = voterRepository.save(voter);
		
		return modelMapper.map(saverVoter, VoterDto.class);
	}

	@Override
	public List<Voter> fetchAllVoter() {
		List<Voter> voters = voterRepository.findAll();
		List<VoterDto> voterDtos = new ArrayList<>();
		for(Voter voter : voters) {
			VoterDto voterDto = modelMapper.map(voter, VoterDto.class);
			voterDtos.add(voterDto);
		}
		return voters;
	}

}
