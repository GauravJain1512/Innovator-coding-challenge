package com.voter.registration.config;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.voter.registration.entity.User;
import com.voter.registration.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found with email: "+username));
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRoleToAutority(user));
	}

	private Collection<? extends GrantedAuthority> mapRoleToAutority(User user) {
		
		List<SimpleGrantedAuthority> authorities = user.getRoles().stream().map(role-> new SimpleGrantedAuthority("ROLE_"+role.getRoleName())).collect(Collectors.toList());
		return authorities;
	}

}
