package com.voter.registration.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Voter {
	
	@Id
	@SequenceGenerator(name = "voter_sequence", sequenceName = "voter_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voter_sequence")
	private Long voterId;
	
	private String voterName;
	
	private LocalDate dateOfBirth;
	
	private String city;
	
	private boolean registrationStatus;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "eligible_elections",
	joinColumns = @JoinColumn(name = "voterId", referencedColumnName = "voterId"),
	inverseJoinColumns = @JoinColumn(name = "electionId", referencedColumnName = "electionId"))
	private List<Election> elections = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "constituencyId", referencedColumnName = "constituencyId")
	private Constituency constituency;

	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER
			)
	@JoinColumn(
			name = "userId",
			referencedColumnName = "userId"
			)
	private User user;
}
