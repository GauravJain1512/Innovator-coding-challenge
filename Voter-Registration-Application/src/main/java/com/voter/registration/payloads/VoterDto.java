package com.voter.registration.payloads;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.voter.registration.entity.Constituency;
import com.voter.registration.entity.Election;
import com.voter.registration.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoterDto {

	private Long voterId;
	@NotEmpty
	@Size(min = 2, message = "Please Enter valid Name" )
	private String voterName;
	@NotNull
	private LocalDate dateOfBirth;
	@NotEmpty
	private String city;
	
	private boolean registrationStatus;
	
	private List<Election> elections = new ArrayList<>();
	
	private Constituency constituency;
	@JsonIgnore
	private User user;
}
