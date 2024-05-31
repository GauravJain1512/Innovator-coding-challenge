package com.voter.registration.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"username","email"}))
public class User {
	
	@Id
	@SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	private Long userId;
	
	private String username;
	
	private String email;
	
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", 
	joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userId"),
	inverseJoinColumns = @JoinColumn(name ="roleId", referencedColumnName = "roleId"))
	private Set<Role> roles = new HashSet<>();
	

}
